package br.com.xy.inc.domain;

import br.com.xy.inc.infrastructure.Validator;
import org.hibernate.validator.constraints.NotBlank;

public class Name {

    @NotBlank
    private String value;

    public Name(String value) {
        this.value = value;
        new Validator().validate(this);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean verifyValue() {
        return this.value == null;
    }
}
