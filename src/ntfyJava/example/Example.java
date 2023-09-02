package ntfyJava.example;

import ntfyJava.NotificationClient;
import ntfyJava.core.Client;
import ntfyJava.exception.NtfyException;
import ntfyJava.model.Action;
import ntfyJava.model.NtfyRequest;
import ntfyJava.model.PRIORITY;

import java.util.ArrayList;
import java.util.List;

public class Example {

    public static void main(String[] args) throws NtfyException {
        Client client =   new NotificationClient().getClient();
        NtfyRequest request = new NtfyRequest();
        request.setTopic("test_ntfy");
        request.setMessage("Look ma, **bold text**, *italics*, ...");
        request.setHost(null);
        request.setTitle("This is the obj msg");
        request.setPriority(PRIORITY.MAX);

        Action action = new Action();
        action.setAction("view");
        action.setLabel("Open github");
        action.setUrl("https://github.com/MaheshBabu11/ntfy-java");
        action.setClear(true);

        List<Action> actions = new ArrayList<>(List.of(action));
        List<String> tags = new ArrayList(List.of("+1","warning"));
        request.setTags(tags);
        request.setMarkdown(true);
        request.setActions(actions);
        client.sendNotification(request);
    }
}
