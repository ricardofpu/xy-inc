package br.com.xy.inc.global.exception.handler;

import br.com.xy.inc.global.exception.error.ExceptionResponse;
import br.com.xy.inc.global.exception.utils.ValidationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@ControllerAdvice
@ResponseBody
public class ValidationExceptionHandler extends WebMvcConfigurerAdapter {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse processValidationError(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setFields(ValidationUtil.createErrorsList(bindingResult));
        return response;
    }
}
