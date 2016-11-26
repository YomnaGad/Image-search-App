package com.example.android.searchimg.data.model;

/**
 * Created by abdulaziz on 11/26/16.
 */

public class User {

    private String username;
    private String mail;
    private String password;

    public User(String username, String mail, String password) {
        this.username = username;
        this.mail = mail;
        this.password = password;
    }

    public User(String mail, String password) {
        this("", mail, password);
    }

    public User() {
        this("", "", "");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
