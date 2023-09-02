package ntfyJava.service;

import ntfyJava.exception.NtfyConnectionException;
import ntfyJava.model.PRIORITY;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class PubServiceImpl implements PubService {
    private static final Logger logger = Logger.getLogger(PubServiceImpl.class.getName());

    String response = null;
    String url = null;

    @Override
    public String publish(String message, String topic, String host) {
        return publishMessage(message, topic, host, null, PRIORITY.DEFAULT);
    }

    @Override
    public String publish(String message, String topic, String host, String title) {
        return publishMessage(message, topic, host, title, PRIORITY.DEFAULT);
    }

    @Override
    public String publish(String message, String topic, String host, String title, PRIORITY priority) {
        return publishMessage(message, topic, host, title, priority);
    }

    private String publishMessage(String message, String topic, String host, String title, PRIORITY priority) {
        try {
            if (null == host) {
                url = NtfyConstants.DEFAULT_URL;
                host = NtfyConstants.DEFAULT_HOST;
            } else {
                url = NtfyConstants.HTTPS + host + "/";
            }
            response = sendPublishRequest(url + topic, message, host, title, priority);
        } catch (IOException e) {
            logger.severe(NtfyConstants.CONNECTION_ERROR_MSG);
        } catch (NtfyConnectionException e) {
            logger.severe(NtfyConstants.NTFY_CONNECTION_ERROR_MSG);
        }
        return response;
    }

    private static String sendPublishRequest(String url, String message, String host, String title, PRIORITY priority) throws IOException, NtfyConnectionException {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty(NtfyConstants.HOST, host);
            //con.setRequestProperty("Authorization","Bearer tk_kukgyojacd5t2ybcz2prep0i7tjcu");
            if (null != title) {
                con.setRequestProperty(NtfyConstants.TITLE, title);
            }
            con.setRequestProperty(NtfyConstants.PRIORITY, String.valueOf(priority.getLevel()));
            con.setRequestProperty(NtfyConstants.CONTENT_TYPE, NtfyConstants.CONTENT_TYPE_VALUE);
            // Enable input/output streams
            con.setDoOutput(true);

            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(message);
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
