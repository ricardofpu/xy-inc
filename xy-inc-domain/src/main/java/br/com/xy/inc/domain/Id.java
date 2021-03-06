package br.com.xy.inc.domain;

import java.util.UUID;

public class Id {

    private String value;

    public Id() {
        this.value = UUID.randomUUID().toString();
    }

    public Id(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
