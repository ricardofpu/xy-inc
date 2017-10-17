package br.com.xy.inc.domain;

import javax.validation.Valid;

public class Coordinate {

    private Integer value;

    public Coordinate(@Valid Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
