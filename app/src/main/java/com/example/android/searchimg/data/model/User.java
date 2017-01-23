package com.example.android.searchimg.data.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("username")
    private String username;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("password")
    private String password;
    @SerializedName("email")
    private String mail;
    @SerializedName("image")
    private String image;

    public User(String firstName, String lastName, String username, String password, String mail,String image) {
        this.firstName = firstName;
        this.lastName = lastName;

        this.username = username;
        this.password = password;
        this.mail = mail;
        this.image = image;
    }


    public User() {
        this("", "", "", "", "", "");
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
