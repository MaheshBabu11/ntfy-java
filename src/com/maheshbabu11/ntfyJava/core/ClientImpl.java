package com.maheshbabu11.ntfyJava.core;

import com.maheshbabu11.ntfyJava.service.NtfyConstants;
import com.maheshbabu11.ntfyJava.service.PubService;

public class ClientImpl implements Client {

    private final PubService pubService;

    public ClientImpl(PubService pubService) {
        this.pubService = pubService;
    }

    @Override
    public String sendNotification(String topic, String message) {
        String host = NtfyConstants.DEFAULT_HOST;
        pubService.publish(message, topic, host);
        return null;
    }
}
