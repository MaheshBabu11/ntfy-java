package ntfyJava.core.exception;

public class NtfyStreamingException extends Exception{
    public NtfyStreamingException() {
        super();
    }

    public NtfyStreamingException(String message) {
        super(message);
    }

    public NtfyStreamingException(String message, Throwable cause) {
        super(message, cause);
    }

    public NtfyStreamingException(Throwable cause) {
        super(cause);
    }

    protected NtfyStreamingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
