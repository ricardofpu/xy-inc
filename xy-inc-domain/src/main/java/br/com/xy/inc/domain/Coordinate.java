package br.com.xy.inc.domain;

import br.com.xy.inc.infrastructure.Validator;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Coordinate {

    @NotNull @Min(0)
    private Integer value;

    public Coordinate(Integer value) {
        this.value = value;
        new Validator().validate(this);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public boolean verifyValue() {
        return this.value == null;
    }
}
