package ntfyJava.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ntfyJava.exception.NtfyConnectionException;
import ntfyJava.exception.NtfyException;
import ntfyJava.model.NtfyRequest;
import ntfyJava.model.PRIORITY;
import ntfyJava.model.RequestModel;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
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
                request.setUrl(NtfyConstants.HTTPS + request.getHost() + "/");
            }
            if (null == request.getPriority()) {
                request.setPriority(PRIORITY.DEFAULT);
            }
            response = sendPublishRequest(request);
        } catch (IOException e) {
            logger.severe(NtfyConstants.CONNECTION_ERROR_MSG);
            throw new NtfyException(e);
        } catch (NtfyConnectionException e) {
            logger.severe(NtfyConstants.NTFY_CONNECTION_ERROR_MSG);
            throw new NtfyException(e);
        }
        return response;

    }

    private String sendPublishRequest(NtfyRequest request) throws NtfyConnectionException, IOException {
        try {
            URL obj = new URL(request.getUrl());
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod(NtfyConstants.POST);
            con.setRequestProperty(NtfyConstants.CONTENT_TYPE, "application/json");

            // Enable input/output streams
            con.setDoOutput(true);

            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(new ObjectMapper().writeValueAsString(createPublishRequest(request)));
                wr.flush();
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
                throw new NtfyConnectionException(NtfyConstants.NTFY_CONNECTION_ERROR_MSG);
            }


        } catch (IOException e) {
            throw new IOException();
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
