package com.example.android.searchimg.ui.home;

import java.util.ArrayList;

/**
 * Created by Yomna on 11/30/2016.
 */
public interface HomeBaseView {
    public void homeSuccess();
    public void homeError(String e);
    public void homeComplete();
    public void userImages(ArrayList<String>userImages);
}
