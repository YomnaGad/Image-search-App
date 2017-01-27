package com.example.android.searchimg.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Yomna on 1/1/2017.
 */

public class Images {

    @SerializedName("image")
    private String image;

    public Images( String image) {
        this.image = image;

    }
    public Images() {
        this("");
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
