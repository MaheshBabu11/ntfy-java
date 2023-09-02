package ntfyJava.service;

import ntfyJava.model.PRIORITY;

public interface PubService {

    String publish(String message, String topic, String host);

    String publish(String message, String topic, String host, String title);

    String publish(String message, String topic, String host, String title, PRIORITY priority);


}
