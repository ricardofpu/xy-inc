package br.com.xy.inc.global.exception.handler;

import br.com.xy.inc.global.exception.NotFoundException;
import br.com.xy.inc.global.exception.error.ExceptionResponse;
import br.com.xy.inc.global.exception.error.ResourceValueResponse;
import br.com.xy.inc.global.exception.utils.ValidationUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler extends WebMvcConfigurerAdapter {

    private final Logger LOG = LogManager.getLogger(this.getClass());

    public GlobalExceptionHandler() {
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ExceptionResponse handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return new ExceptionResponse();
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse handleAccessDeniedException(Exception ex) {
        return new ExceptionResponse("Access denied error");
    }

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResourceValueResponse handleNotFound(NotFoundException ex) {
        ResourceValueResponse response = ex.getResource() == null ? null : new ResourceValueResponse(ex.getResource());
        return response;
    }

    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleInternalException(RuntimeException ex) {
        LOG.error("500 Status Code", ex);
        return new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }
}
