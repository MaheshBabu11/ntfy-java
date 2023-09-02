package ntfyJava.exception;

public class NtfyException extends Exception{
    public NtfyException() {
        super();
    }

    public NtfyException(String message) {
        super(message);
    }

    public NtfyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NtfyException(Throwable cause) {
        super(cause);
    }

    protected NtfyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
