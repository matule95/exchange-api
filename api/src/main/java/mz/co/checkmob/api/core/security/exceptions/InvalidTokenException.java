package mz.co.checkmob.api.core.security.exceptions;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() { super(); }
    public InvalidTokenException(String message) {
        super(message);
    }
}
