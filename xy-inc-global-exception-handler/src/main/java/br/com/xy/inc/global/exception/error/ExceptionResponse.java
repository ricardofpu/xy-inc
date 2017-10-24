package br.com.xy.inc.global.exception.error;

import br.com.xy.inc.global.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {

    private Integer code;
    private String errorMessage;
    private Map<String, List<String>> fields;
    private MessageSource messageSource;

    public ExceptionResponse() {
    }

    public ExceptionResponse(MessageSource messageSource) {
        this.messageSource = messageSource;
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

    public ExceptionResponse(Map<String, List<String>> fields) {
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

    public ExceptionResponse fromBusinessExceptionResult(BusinessException ex) {
        if (ex.getErrors() != null) {
            Map<String, List<String>> fields = ex.getErrors().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, (entry) ->
                    entry.getValue().stream().map(this::getMessageResource).collect(Collectors.toCollection(LinkedList::new))));
            return new ExceptionResponse(fields);
        }
        return null;
    }

    private String getMessageResource(ErrorCode code) {
        return this.getMessageResource(code.getKey());
    }

    private String getMessageResource(String key) {
        Locale locale = LocaleContextHolder.getLocale();

        try {
            return this.messageSource.getMessage(key, new Object[0], locale);
        } catch (NoSuchMessageException var4) {
            return key;
        }
    }
}
