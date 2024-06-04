package ntfyJava;

import ntfyJava.core.publish.PubClient;
import ntfyJava.core.publish.PubClientImpl;
import ntfyJava.core.model.ClientType;
import ntfyJava.core.publish.PubServiceImpl;

public final class NtfyClient {

    private PubClient pubClient;


    public NtfyClient(ClientType type) {
        switch (type) {
            case PUB:
                PubServiceImpl pubService = new PubServiceImpl();
                this.pubClient = new PubClientImpl(pubService);
                break;
            case SUB:
                break;



            default:
                throw new IllegalArgumentException("Invalid client type");
        }

    }

    public PubClient getClient() {
        return pubClient;
    }
}

