package com.simplerest.protocol;

/**
 * Created by josemazzetti on 28/11/2016.
 */
public class ErrorObject {

    private String code;
    private String message;

    private ErrorObject() {
    }

    public ErrorObject(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
