package com.khurana.nikhil.tuhub;

/**
 * Created by nikhil on 22-04-2016.
 */
public class Society
{
    String name,password;

    public Society(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Society() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
