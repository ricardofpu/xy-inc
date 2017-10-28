package br.com.xy.inc.global.exception.utils;

import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import java.util.*;

public class ValidationUtil {

    public static Map<String, List<String>> fromBindingsResult(List<FieldError> errors) {
        Map<String, List<String>> fields = new LinkedHashMap();
        errors.forEach((field) ->
                fields.computeIfAbsent(field.getField(), (k) -> new LinkedList()).add(field.getDefaultMessage()));
        return fields;
    }

    public static Map<String, List<String>> fromBindingsResult(Set<ConstraintViolation<?>> constraintViolations) {
        Map<String, List<String>> fields = new LinkedHashMap();
        constraintViolations.forEach((violation) ->
                fields.computeIfAbsent(violation.getPropertyPath().toString(), (k) ->
                        new LinkedList()).add(violation.getMessage()));
        return fields;
    }


}
