package ntfyJava.core.exception;

public class NtfyConnectionException extends Exception {

    public NtfyConnectionException() {
        super();
    }

    public NtfyConnectionException(String message) {
        super(message);
    }

    public NtfyConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NtfyConnectionException(Throwable cause) {
        super(cause);
    }
}
