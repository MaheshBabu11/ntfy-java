package com.maheshbabu11.ntfyJava;

import com.maheshbabu11.ntfyJava.core.Client;
import com.maheshbabu11.ntfyJava.core.ClientImpl;
import com.maheshbabu11.ntfyJava.service.PubService;

public final class NotificationClient {

    public static Client getClient() {

        PubService pubService = new PubService();
        return new ClientImpl(pubService);

    }
}
