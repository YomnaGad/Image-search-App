package com.example.android.searchimg.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Yomna on 1/24/2017.
 */

public class Request {


    @SerializedName("user")
    private User user ;

    public Request(User user) {
        this.user = user;
    }

   /* public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/
}
