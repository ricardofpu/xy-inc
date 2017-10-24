package br.com.xy.inc.global.exception;

import br.com.xy.inc.global.exception.error.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BusinessException extends RuntimeException {

    private static final Logger LOG = LogManager.getLogger(BusinessException.class);

    private Map<String, List<ErrorCode>> errors;
    private ErrorCode errorCode;
    private String[] parameters;

    public BusinessException(Map<String, List<ErrorCode>> errors) throws RuntimeException {
        super(tryBuildLogMessageAsJson(errors));
        this.errors = errors;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public static BusinessException of(Map<String, List<ErrorCode>> errors) {
        return new BusinessException(errors);
    }

    public BusinessException(ErrorCode error) {
        super(tryBuildLogMessageAsJson(error));
        this.errorCode = error;
    }

    public static BusinessException of(String fieldName, ErrorCode error) {
        Map<String, List<ErrorCode>> errors = new LinkedHashMap<>();
        errors.computeIfAbsent(fieldName, (k) -> new LinkedList()).add(error);
        return of(errors);
    }

    public Map<String, List<ErrorCode>> getErrors() {
        return this.errors;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public String[] getParameters() {
        return this.parameters;
    }

    private static String tryBuildLogMessageAsJson(Object object) {
        try {
            return (new ObjectMapper()).writeValueAsString(object);
        } catch (Exception var2) {
            return null;
        }
    }

}

