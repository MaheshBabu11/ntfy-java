package ntfyJava;

import ntfyJava.core.Client;
import ntfyJava.core.ClientImpl;
import ntfyJava.service.PubServiceImpl;

public final class NotificationClient {

    public Client getClient() {
        PubServiceImpl pubService = new PubServiceImpl();
        return new ClientImpl(pubService);

    }
}
