package br.com.xy.inc.global.exception.error;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {

    private Integer code;
    private String errorMessage;
    private Map<String, List<String>> fields;

    public ExceptionResponse() {
    }

    public ExceptionResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ExceptionResponse(Integer code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public ExceptionResponse(Integer code, String errorMessage, Map<String, List<String>> fields) {
        this.code = code;
        this.errorMessage = errorMessage;
        this.fields = fields;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Map<String, List<String>> getFields() {
        return fields;
    }

    public void setFields(Map<String, List<String>> fields) {
        this.fields = fields;
    }
}
