package com.simplerest.protocol;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by josemazzetti on 05/12/2016.
 */
public class PutRequest {

    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private Integer age;
    @NotNull
    private Timestamp startdate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Timestamp getStartdate() {
        return startdate;
    }

    public void setStartdate(Timestamp startdate) {
        this.startdate = startdate;
    }
}
