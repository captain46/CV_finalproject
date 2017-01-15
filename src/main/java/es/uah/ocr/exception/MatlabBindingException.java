package es.uah.ocr.exception;

/**
 * @author bnjm@harmless.ninja - 1/15/17.
 */
public class MatlabBindingException extends RuntimeException {
    public MatlabBindingException() {
        super();
    }

    public MatlabBindingException(String message) {
        super(message);
    }

    public MatlabBindingException(String message, Throwable cause) {
        super(message, cause);
    }

    public MatlabBindingException(Throwable cause) {
        super(cause);
    }

    protected MatlabBindingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
