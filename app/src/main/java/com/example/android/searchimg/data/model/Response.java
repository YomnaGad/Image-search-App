package com.example.android.searchimg.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Yomna on 12/20/2016.
 */

public class Response {
    @SerializedName("status")
    public int status;
    @SerializedName("user")
    public User user;
    @SerializedName("image")
    public Images image;
    @SerializedName("token")
    public String token;
    @SerializedName("images")
    public ArrayList<String> images;
    @SerializedName("detail")
    public String detail;

}
