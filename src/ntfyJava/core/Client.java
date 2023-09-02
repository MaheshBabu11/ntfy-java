package ntfyJava.core;

import ntfyJava.exception.NtfyException;
import ntfyJava.model.NtfyRequest;

public interface Client {
    String sendNotification(NtfyRequest ntfyRequest) throws NtfyException;
}
