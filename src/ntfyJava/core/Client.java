package ntfyJava.core;

import ntfyJava.exception.NtfyException;
import ntfyJava.model.PRIORITY;

public interface Client {
    String sendNotification(String topic, String message) throws NtfyException;

    String sendNotification(String topic, String message, String host) throws NtfyException;

    String sendNotification(String topic, String message, String host, String title) throws NtfyException;

    String sendNotification(String topic, String message, String host, String title, PRIORITY priority) throws NtfyException;

    String sendNotification(String topic, String message, String host, String title, PRIORITY priority, String tags) throws NtfyException;


}
