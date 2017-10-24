package br.com.xy.inc.infrastructure;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import java.util.Set;

public class Validator {

    javax.validation.Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public final void validate(Object instance) {
        Set validate = this.getValidator().validate(instance);
        if (!validate.isEmpty()) {
            throw new ConstraintViolationException(validate);
        }
    }

    private javax.validation.Validator getValidator() {
        return validator;
    }

}