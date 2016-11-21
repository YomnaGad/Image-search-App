package com.example.android.searchimg.ui.login;

/**
 * Created by Yomna on 11/21/2016.
 */
public interface LoginBaseView {
    public String getEmail();
    public void showEmailError(int resId);
    public String getPassword();
    public void showPasswordError(int resId);
    public void startMainActivity();
    public void showLoginError(int resId);
}
