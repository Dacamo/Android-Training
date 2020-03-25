package com.DTO;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserDTO implements Serializable {


    private String name;
    private String lastName;
    private String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
