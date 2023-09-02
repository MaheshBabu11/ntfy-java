package ntfyJava.example;

import ntfyJava.NotificationClient;
import ntfyJava.core.Client;
import ntfyJava.exception.NtfyException;
import ntfyJava.model.NtfyRequest;
import ntfyJava.model.PRIORITY;

public class Example {

    public static void main(String[] args) throws NtfyException {
        Client client =   new NotificationClient().getClient();
        NtfyRequest request = new NtfyRequest();
        request.setTopic("test_ntfy");
        request.setMessage("Look ma, **bold text**, *italics*, ...");
        request.setHost(null);
        request.setTitle("This is the obj msg");
        request.setPriority(PRIORITY.MAX);
        request.setTags("+1,warning");
        request.setMarkdown(true);
        client.sendNotification(request);
    }
}
