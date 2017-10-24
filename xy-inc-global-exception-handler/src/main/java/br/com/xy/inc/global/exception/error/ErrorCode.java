package br.com.xy.inc.global.exception.error;

public class ErrorCode {

    private final String code;
    private final String message;

    public ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

