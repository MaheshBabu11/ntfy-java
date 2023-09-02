package ntfyJava.core;

import ntfyJava.exception.NtfyException;
import ntfyJava.model.PRIORITY;
import ntfyJava.service.PubService;

import java.util.logging.Logger;

public class ClientImpl implements Client {

    private static final Logger logger = Logger.getLogger(ClientImpl.class.getName());
    private final PubService pubService;

    public ClientImpl(PubService pubService) {
        this.pubService = pubService;
    }

    String response = null;

    @Override
    public String sendNotification(String topic, String message) throws NtfyException {
        response = pubService.publish(message, topic, null);
        logger.info("Response from server : " + response);
        return response;
    }

    @Override
    public String sendNotification(String topic, String message, String host) throws NtfyException {
        response = pubService.publish(message, topic, host);
        logger.info("Response from server : " + response);
        return response;
    }

    @Override
    public String sendNotification(String topic, String message, String host, String title) throws NtfyException {
        response = pubService.publish(message, topic, host, title);
        logger.info("Response from server : " + response);
        return response;
    }

    @Override
    public String sendNotification(String topic, String message, String host, String title, PRIORITY priority) throws NtfyException {
        response = pubService.publish(message, topic, host, title, priority);
        logger.info("Response from server : " + response);
        return response;
    }

    @Override
    public String sendNotification(String topic, String message, String host, String title, PRIORITY priority, String tags) throws NtfyException {
        response = pubService.publish(message, topic, host, title, priority, tags);
        logger.info("Response from server : " + response);
        return response;
    }
}
