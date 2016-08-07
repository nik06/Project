package com.khurana.nikhil.tuhub;

/**
 * Created by nikhil on 21-04-2016.
 */
public class User {
    String username,email,roll,contact,password,confirm;

    public User() {
    }

    public User(String username, String email, String roll, String contact, String password, String confirm) {
        this.username = username;
        this.email = email;
        this.roll = roll;
        this.contact = contact;
        this.password = password;
        this.confirm = confirm;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
}
