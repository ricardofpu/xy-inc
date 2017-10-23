package br.com.xy.inc.global.exception.utils;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.stream.Collectors;

public class ValidationUtil {

    public static List<String> createErrorsList(Errors errors) {
        return errors.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
    }
}
