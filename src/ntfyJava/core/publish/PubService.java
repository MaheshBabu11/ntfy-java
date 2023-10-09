package ntfyJava.core.publish;

import ntfyJava.core.exception.NtfyException;
import ntfyJava.core.model.NtfyRequest;

public interface PubService {
    String publish(NtfyRequest ntfyRequest) throws NtfyException;
}
