package ntfyJava.core;

import ntfyJava.exception.NtfyException;
import ntfyJava.model.NtfyRequest;
import ntfyJava.service.PubService;

import java.util.logging.Logger;

public class ClientImpl implements Client {

    private static final Logger logger = Logger.getLogger(ClientImpl.class.getName());
    private final PubService pubService;

    public ClientImpl(PubService pubService) {
        this.pubService = pubService;
    }

    @Override
    public String sendNotification(NtfyRequest ntfyRequest) throws NtfyException {
        String response = pubService.publish(ntfyRequest);
        if (null != response) {
            logger.info("Response from server : " + response);
        }
        return response;
    }
}
