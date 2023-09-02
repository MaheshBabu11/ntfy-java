package ntfyJava.core;

import ntfyJava.model.PRIORITY;

public interface Client {
    String sendNotification(String topic, String message);

    String sendNotification(String topic, String message, String host);

    String sendNotification(String topic, String message, String host, String title);

    String sendNotification(String topic, String message, String host, String title, PRIORITY priority);


}
