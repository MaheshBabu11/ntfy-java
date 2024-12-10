package ntfyJava.example;


import ntfyJava.NtfyClient;
import ntfyJava.core.publish.PubClient;
import ntfyJava.core.exception.NtfyException;
import ntfyJava.core.model.*;

import java.util.ArrayList;
import java.util.List;

public class PublishExample {

    public static void main(String[] args) throws NtfyException {
        PubClient client = new NtfyClient(ClientType.PUB).getClient();
        NtfyRequest request = new NtfyRequest();
        request.setTopic("test_ntfy2");
        request.setMessage("Look ma, **bold text**, *italics*, emoji 🥳 🤟 🕊️...");
        request.setTitle("This is the obj msg");
        request.setPriority(PRIORITY.MAX);
        request.setAttach("https://media.licdn.com/dms/image/D4E03AQEZTNXuX3kG7g/profile-displayphoto-shrink_400_400/0/1669618932666?e=1699488000&v=beta&t=q2z_UDFvwTZa02SligKZqgwk66BjuXQZxWtQF_K1Jqw");
        request.setFileName("Screenshot.png");
        request.setIcon("https://styles.redditmedia.com/t5_32uhe/styles/communityIcon_xnt6chtnr2j21.png");
        request.setEmail("mahesh.b.pec@gmail.com");
        request.setPhone("");
        request.setAuthToken("");

        Action action = new Action();
        action.setAction(ACTIONS.VIEW);
        action.setLabel("Open github");
        action.setUrl("https://github.com/MaheshBabu11/ntfy-java");
        action.setClear(true);

        List<Action> actions = new ArrayList<>(List.of(action));
        List<String> tags = new ArrayList<>(List.of("+1", "warning"));
        request.setTags(tags);
        request.setMarkdown(true);
        request.setActions(actions);
        client.sendNotification(request);
    }
}
