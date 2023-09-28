package ntfyJava.core;

import ntfyJava.core.exception.NtfyException;
import ntfyJava.core.model.NtfyRequest;

public interface PubClient {
    String sendNotification(NtfyRequest ntfyRequest) throws NtfyException;
}
