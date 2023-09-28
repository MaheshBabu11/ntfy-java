package ntfyJava.core.service;

import ntfyJava.core.exception.NtfyException;
import ntfyJava.core.model.NtfyRequest;

public interface PubService {
    String publish(NtfyRequest ntfyRequest) throws NtfyException;
}
