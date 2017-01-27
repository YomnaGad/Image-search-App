package com.example.android.searchimg.ui.login;

/**
 * Created by Yomna on 11/21/2016.
 */
public interface LoginBaseView {
    public void loginSuccess(String  token);
    public void loginError(String e);
    public void loginComplete();
}
