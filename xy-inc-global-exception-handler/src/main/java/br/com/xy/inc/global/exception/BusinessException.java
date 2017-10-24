package br.com.xy.inc.global.exception;

import br.com.xy.inc.global.exception.error.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class BusinessException extends RuntimeException {

    private ErrorCode errorCode;

    public BusinessException() {
    }

    public BusinessException(ErrorCode error, Throwable cause) throws RuntimeException {
        super(tryBuildLogMessageAsJson(error), cause);
        this.errorCode = error;
    }

    public BusinessException(ErrorCode error) throws RuntimeException {
        super(tryBuildLogMessageAsJson(error));
        this.errorCode = error;
    }

    public BusinessException(Throwable cause) { super(cause); }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    private static String tryBuildLogMessageAsJson(Object object) {
        try {
            return (new ObjectMapper()).writeValueAsString(object);
        } catch (Exception var2) {
            return null;
        }
    }

}

