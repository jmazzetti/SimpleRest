package com.simplerest.protocol;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RequestObject<T> {

    @NotNull
    @Size(min=1, max=50)
    private String method;
    @NotNull
    @Size(min=1, max=50)
    private String id;
    @NotNull
    @Valid
    private T parameters;


    public RequestObject() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public T getParameters() {
        return parameters;
    }

    public void setParameters(T parameters) {
        this.parameters = parameters;
    }

}

