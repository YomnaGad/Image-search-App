package com.example.android.searchimg.ui.register;

/**
 * Created by Yomna on 11/22/2016.
 */
public interface RegisterBaseView {

    public String getUsername();
    public void showUsernameError(int resId);

    public String getEmail();
    public void showEmailError(int resId);

    public String getPassword();
    public void showPasswordError(int resId);

    public String getConfirmPassword();
    public void showConfirmPasswordError(int resId);


    public void startMainActivity();


    public void showRegisterError(int resId);

    void showPasswordMatchError(int resId);
}
