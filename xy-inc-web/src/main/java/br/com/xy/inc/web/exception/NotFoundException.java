package br.com.xy.inc.web.exception;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
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
