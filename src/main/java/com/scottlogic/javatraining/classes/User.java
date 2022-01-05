package com.scottlogic.javatraining.classes;

public class User {

    public String username;
    public String password;
    public String token;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User {" +
                "Username: " + username +
                ", Password: " + password +
                ", Token: " + token +
                '}';
    }
}
