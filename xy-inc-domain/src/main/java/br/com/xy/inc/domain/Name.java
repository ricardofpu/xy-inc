package br.com.xy.inc.domain;

import javax.validation.Valid;

public class Name {

    private String value;

    public Name(@Valid String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
