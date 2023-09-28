package ntfyJava;

import ntfyJava.core.PubClient;
import ntfyJava.core.PubClientImpl;
import ntfyJava.core.model.ClientType;
import ntfyJava.core.service.PubServiceImpl;

public final class NtfyClient {

    private final PubClient client;

    public NtfyClient(ClientType type) {
        switch (type) {
            case PUB:
                PubServiceImpl pubService = new PubServiceImpl();
                this.client = new PubClientImpl(pubService);
                break;
            default:
                throw new IllegalArgumentException("Invalid client type");
        }

    }

    public PubClient getClient() {
        return client;
    }
}

