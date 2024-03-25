package exception;

public class InvalideBotCountException extends RuntimeException{

    public InvalideBotCountException() {
    }

    public InvalideBotCountException(String message) {
        super(message);
    }

    public InvalideBotCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalideBotCountException(Throwable cause) {
        super(cause);
    }

    public InvalideBotCountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
