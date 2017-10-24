package br.com.xy.inc.global.exception.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import java.util.*;
import java.util.stream.Collectors;

public class ValidationUtil {

    private final MessageSource messageSource;

    public ValidationUtil(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public static List<String> fromBindingsResult(Errors errors) {
        return errors.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
    }

    public static Map<String, List<String>> fromBindingsResult(List<FieldError> errors) {
        Map<String, List<String>> fields = new LinkedHashMap();
        errors.forEach((field) -> {
            (fields.computeIfAbsent(field.getField(), (k) -> new LinkedList())).add(field.getDefaultMessage());
        });
        return fields;
    }

    public static Map<String, List<String>> fromBindingsResult(Set<ConstraintViolation<?>> constraintViolations) {
        Map<String, List<String>> fields = new LinkedHashMap();
        constraintViolations.forEach((violation) -> {
            fields.computeIfAbsent(violation.getPropertyPath().toString(), (k) -> new LinkedList()).add(violation.getMessage());
        });
        return fields;
    }


}
