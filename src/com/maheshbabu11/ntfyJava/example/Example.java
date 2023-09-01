package com.maheshbabu11.ntfyJava.example;

import com.maheshbabu11.ntfyJava.NotificationClient;
import com.maheshbabu11.ntfyJava.core.Client;

public class Example {

    public static void main(String[] args) {
        Client client = new NotificationClient().getClient();
        client.sendNotification("test_ntfy","test ntfty");
    }
}
