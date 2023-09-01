package com.maheshbabu11.ntfyJava.core;

import com.maheshbabu11.ntfyJava.service.NtfyConstants;
import com.maheshbabu11.ntfyJava.service.PubService;

import java.util.logging.Logger;

public class ClientImpl implements Client {

    private static final Logger logger = Logger.getLogger(ClientImpl.class.getName());
    private final PubService pubService;

    public ClientImpl(PubService pubService) {
        this.pubService = pubService;
    }

    String response = null;
    @Override
    public String sendNotification(String topic, String message) {

        String host = NtfyConstants.DEFAULT_HOST;
        response = pubService.publish(message, topic, host);
        logger.info("Response from server : " + response.toString());
        return response;
    }

    @Override
    public  String sendNotification(String topic,String message, String host){
        response = pubService.publish(message, topic, host);
        logger.info("Response from server : " + response.toString());
        return response;
    }
}
