package ntfyJava.service;

import ntfyJava.exception.NtfyConnectionException;
import ntfyJava.exception.NtfyException;
import ntfyJava.model.NtfyRequest;
import ntfyJava.model.PRIORITY;

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
                request.setUrl(NtfyConstants.DEFAULT_URL + request.getTopic());
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
            con.setRequestProperty(NtfyConstants.HOST, request.getHost());
            if (null != request.getTitle()) {
                con.setRequestProperty(NtfyConstants.TITLE, request.getTitle());
            }
            if (null != request.getTags()) {
                con.setRequestProperty(NtfyConstants.TAGS, request.getTags());
            }
            if(request.isMarkdown()){
                con.setRequestProperty(NtfyConstants.MARKDOWN, NtfyConstants.YES);
            }
            con.setRequestProperty(NtfyConstants.PRIORITY, String.valueOf(request.getPriority().getLevel()));
            con.setRequestProperty(NtfyConstants.CONTENT_TYPE, NtfyConstants.CONTENT_TYPE_VALUE);
            // Enable input/output streams
            con.setDoOutput(true);

            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(request.getMessage());
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


}
