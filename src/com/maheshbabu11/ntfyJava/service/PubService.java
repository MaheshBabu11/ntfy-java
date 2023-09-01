package com.maheshbabu11.ntfyJava.service;

import com.maheshbabu11.ntfyJava.exception.NtfyConnectionException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class PubService {

    private static final Logger logger = Logger.getLogger(PubService.class.getName());

    public String publish(String message, String topic, String host) {

        String response = null;
        try {
            response = sendPublishRequest(NtfyConstants.DEFAULT_URL + "/" + topic, message, host);
        } catch (IOException e) {
            logger.severe(NtfyConstants.CONNECTION_ERROR_MSG);
        } catch (NtfyConnectionException e) {
            logger.severe(NtfyConstants.NTFY_CONNECTION_ERROR_MSG);
        }
        return response;

    }

    public static String sendPublishRequest(String url, String message, String host) throws IOException, NtfyConnectionException {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod(NtfyConstants.POST);
            con.setRequestProperty(NtfyConstants.HOST, host);
            //con.setRequestProperty("Authorization","Bearer tk_kukgyojacd5t2ybcz2prep0i7tjcu");
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
