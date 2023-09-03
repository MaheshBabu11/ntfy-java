package ntfyJava.example;

import ntfyJava.NotificationClient;
import ntfyJava.core.Client;
import ntfyJava.exception.NtfyException;
import ntfyJava.model.ACTIONS;
import ntfyJava.model.Action;
import ntfyJava.model.NtfyRequest;
import ntfyJava.model.PRIORITY;

import java.util.ArrayList;
import java.util.List;

public class Example {

    public static void main(String[] args) throws NtfyException {
        Client client = new NotificationClient().getClient();
        NtfyRequest request = new NtfyRequest();
        request.setTopic("test_ntfy");
        request.setMessage("Look ma, **bold text**, *italics*, ...");
        request.setHost(null);
        request.setTitle("This is the obj msg");
        request.setPriority(PRIORITY.MAX);
//        request.setAttach("https://media.licdn.com/dms/image/D4E03AQEZTNXuX3kG7g/profile-displayphoto-shrink_400_400/0/1669618932666?e=1699488000&v=beta&t=q2z_UDFvwTZa02SligKZqgwk66BjuXQZxWtQF_K1Jqw");
//        request.setFileName("Screenshot.png");
        request.setIcon("https://styles.redditmedia.com/t5_32uhe/styles/communityIcon_xnt6chtnr2j21.png");
        request.setEmail("mahesh.b.pec@gmail.com");
        request.setPhone("");

        Action action = new Action();
        action.setAction(ACTIONS.VIEW);
        action.setLabel("Open github");
        action.setUrl("https://github.com/MaheshBabu11/ntfy-java");
        action.setClear(true);

        List<Action> actions = new ArrayList<>(List.of(action));
        List<String> tags = new ArrayList(List.of("+1", "warning"));
        //request.setTags(tags);
        request.setMarkdown(true);
        request.setActions(actions);
        client.sendNotification(request);
    }
}
