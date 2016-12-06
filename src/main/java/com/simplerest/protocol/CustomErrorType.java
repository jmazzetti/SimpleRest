package com.simplerest.protocol;

import org.springframework.http.HttpStatus;

/**
 * Created by josemazzetti on 06/12/2016.
 */
public class CustomErrorType {

    private int status;
    private String message;
    private HttpStatus httpStatus;

    public CustomErrorType(int value, String message) {

    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
