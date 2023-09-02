package ntfyJava.service;

import ntfyJava.exception.NtfyException;
import ntfyJava.model.PRIORITY;

public interface PubService {

    String publish(String message, String topic, String host) throws NtfyException;

    String publish(String message, String topic, String host, String title) throws NtfyException;

    String publish(String message, String topic, String host, String title, PRIORITY priority) throws NtfyException;

    String publish(String message, String topic, String host, String title, PRIORITY priority,String tagsr̥) throws NtfyException;


}
