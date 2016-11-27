package com.example.android.searchimg.ui.register;

/**
 * Created by Yomna on 11/22/2016.
 */
public interface RegisterBaseView {

    public void registerSuccess();
    public void registerError(String e);
    public void registerComplete();
    public String getConfirmPassword();
}
