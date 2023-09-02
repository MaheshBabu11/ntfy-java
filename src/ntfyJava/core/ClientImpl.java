package ntfyJava.core;

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
    public String sendNotification(String topic, String message) {
        response = pubService.publish(message, topic, null);
        logger.info("Response from server : " + response);
        return response;
    }

    @Override
    public String sendNotification(String topic, String message, String host) {
        response = pubService.publish(message, topic, host);
        logger.info("Response from server : " + response);
        return response;
    }

    @Override
    public String sendNotification(String topic, String message, String host, String title) {
        response = pubService.publish(message, topic, host, title);
        logger.info("Response from server : " + response);
        return response;
    }

    @Override
    public String sendNotification(String topic, String message, String host, String title, PRIORITY priority) {
        response = pubService.publish(message, topic, host, title, priority);
        logger.info("Response from server : " + response);
        return response;
    }
}
