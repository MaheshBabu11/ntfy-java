package ntfyJava;

import ntfyJava.core.Client;
import ntfyJava.core.ClientImpl;
import ntfyJava.service.PubServiceImpl;

public final class NotificationClient {

    public static Client getClient() {
        PubServiceImpl pubService = new PubServiceImpl();
        return new ClientImpl(pubService);

    }
}
