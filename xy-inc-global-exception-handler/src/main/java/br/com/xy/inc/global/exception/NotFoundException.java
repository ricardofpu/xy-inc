package br.com.xy.inc.global.exception;

public final class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(final String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(final String message) {
        super(message);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }
}
