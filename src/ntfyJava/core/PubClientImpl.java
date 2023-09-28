package ntfyJava.core;

import ntfyJava.core.exception.NtfyException;
import ntfyJava.core.model.NtfyRequest;
import ntfyJava.core.service.PubService;

import java.util.logging.Logger;

public class PubClientImpl implements PubClient {

    private static final Logger logger = Logger.getLogger(PubClientImpl.class.getName());
    private final PubService pubService;

    public PubClientImpl(PubService pubService) {
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
