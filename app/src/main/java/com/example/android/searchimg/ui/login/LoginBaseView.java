package com.example.android.searchimg.ui.login;

import com.example.android.searchimg.data.model.User;

/**
 * Created by Yomna on 11/21/2016.
 */
public interface LoginBaseView {
    public void loginSuccess(User user);
    public void loginError(String e);
    public void loginComplete();
}
