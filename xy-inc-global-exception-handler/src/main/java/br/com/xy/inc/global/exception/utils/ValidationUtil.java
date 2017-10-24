package br.com.xy.inc.global.exception.utils;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ValidationUtil {

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
}
