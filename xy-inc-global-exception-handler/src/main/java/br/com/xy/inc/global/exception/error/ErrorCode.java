package br.com.xy.inc.global.exception.error;

public class ErrorCode {

    private final String code;
    private final String key;

    public ErrorCode(String code, String key) {
        this.code = code;
        this.key = key;
    }

    public String getCode() {
        return this.code;
    }

    public String getKey() {
        return this.key;
    }

    public String toString() {
        return this.key;
    }
}

