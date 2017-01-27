package com.example.android.searchimg.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Yomna on 1/24/2017.
 */

public class Data {
    @SerializedName("user")
    private String user;

    @SerializedName("image")
    private String image;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
