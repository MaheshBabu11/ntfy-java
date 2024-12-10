package ntfyJava.core.publish;

import com.fasterxml.jackson.databind.ObjectMapper;
import ntfyJava.core.exception.NtfyConnectionException;
import ntfyJava.core.exception.NtfyException;
import ntfyJava.core.model.NtfyRequest;
import ntfyJava.core.model.PRIORITY;
import ntfyJava.core.model.RequestModel;
import ntfyJava.core.common.NtfyConstants;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class PubServiceImpl implements PubService {
    private static final Logger logger = Logger.getLogger(PubServiceImpl.class.getName());

    @Override
    public String publish(NtfyRequest request) throws NtfyException {
        return publishMessage(request);
    }

    private String publishMessage(NtfyRequest request) throws NtfyException {
        String response = null;
        try {
            if (null == request.getHost()) {
                request.setUrl(NtfyConstants.DEFAULT_URL);
                request.setHost(NtfyConstants.DEFAULT_HOST);
            } else {
                String protocol = request.getHost().contains(NtfyConstants.LOCALHOST) ? NtfyConstants.HTTP : NtfyConstants.HTTPS;
                request.setUrl(protocol + request.getHost() + "/");
            }
            if (null == request.getPriority()) {
                request.setPriority(PRIORITY.DEFAULT);
            }
            System.out.println("Request : " + request);
            response = sendPublishRequest(request);
        } catch (IOException e) {
            logger.severe(NtfyConstants.CONNECTION_ERROR_MSG);
            throw new NtfyException(e);
        } catch (NtfyConnectionException e) {
            logger.severe(NtfyConstants.NTFY_CONNECTION_ERROR_MSG);
            throw new NtfyException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;

    }

    private String sendPublishRequest(NtfyRequest request) throws Exception {
        try {
            URL obj = new URL(request.getUrl());
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod(NtfyConstants.POST);
            con.setRequestProperty(NtfyConstants.CONTENT_TYPE, "application/json; charset=utf-8");

            //handle authentication (if supplied)
            if (request.getAccessToken() != null) {
                con.setRequestProperty("Authorization", "Bearer " + request.getAuthToken());
            } else if (request.getAuthToken() != null) {
                con.setRequestProperty("Authorization", "Basic " + request.getAuthToken());
            }
            // Enable input/output streams
            con.setDoOutput(true);

            try (BufferedOutputStream outputStream = new BufferedOutputStream(con.getOutputStream());) {
                String json = new ObjectMapper().writeValueAsString(createPublishRequest(request));
                outputStream.write(json.getBytes(StandardCharsets.UTF_8));
            }
            // Get the response from the server
            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder response = new StringBuilder();
                try (java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }
                return response.toString();
            } else {
                throw new NtfyConnectionException(NtfyConstants.NTFY_CONNECTION_ERROR_MSG + " : " + responseCode);
            }


        } catch (IOException e) {
            throw new NtfyConnectionException(NtfyConstants.CONNECTION_ERROR_MSG, e);
        }

    }

    private RequestModel createPublishRequest(NtfyRequest request) {
        RequestModel model = new RequestModel();
        model.setTopic(request.getTopic());
        model.setMessage(request.getMessage());
        model.setTitle(request.getTitle());
        model.setTags(request.getTags());
        model.setMarkdown(request.isMarkdown());
        model.setPriority(request.getPriority().getLevel());
        model.setActions(request.getActions());
        model.setAttach(request.getAttach());
        model.setFileName(request.getFileName());
        model.setIcon(request.getIcon());
        model.setEmail(request.getEmail());
        model.setCall(request.getPhone());
        return model;
    }


}
