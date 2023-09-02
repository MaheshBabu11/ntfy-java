package ntfyJava.example;

import ntfyJava.NotificationClient;
import ntfyJava.core.Client;
import ntfyJava.model.PRIORITY;

public class Example {

    public static void main(String[] args) {
        Client client = new NotificationClient().getClient();
        client.sendNotification("test_ntfy", "test ntfty", null, "hello", PRIORITY.MAX);
    }
}
