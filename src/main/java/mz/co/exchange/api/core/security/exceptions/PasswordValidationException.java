package mz.co.exchange.api.core.security.exceptions;

public class PasswordValidationException extends RuntimeException {
    public PasswordValidationException() { super(); }
    public PasswordValidationException(String message) { super(message); }
}
