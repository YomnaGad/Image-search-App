package com.example.android.searchimg.ui.register;

import com.example.android.searchimg.data.model.User;

/**
 * Created by Yomna on 11/22/2016.
 */
public interface RegisterBaseView {

    public void registerSuccess(User user);
    public void registerError(String e);
    public void registerComplete();
    public String getConfirmPassword();


}
