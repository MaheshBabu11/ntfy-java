package ntfyJava.service;

import ntfyJava.exception.NtfyException;
import ntfyJava.model.NtfyRequest;

public interface PubService {
    String publish(NtfyRequest ntfyRequest) throws NtfyException;
}
