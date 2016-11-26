package com.example.android.searchimg.ui.login;

import android.util.Log;

import com.example.android.searchimg.R;
import com.example.android.searchimg.data.DataManager;
import com.example.android.searchimg.data.model.User;

/**
 * Created by Yomna on 11/21/2016.
 */
public class LoginPresenter {
    private LoginBaseView view;
    private DataManager dataManager;

    public LoginPresenter(LoginBaseView view, DataManager dataManager){
        this.view = view;
        this.dataManager = dataManager;
    }

    public void login(User user){
        boolean loginSucceeded = dataManager.login(user.getMail(), user.getPassword());
        Log.i("TAGGGG", "login: "+user.getMail()+"::"+user.getPassword());
        Log.i("TAGGGG", "login: "+loginSucceeded);

        if(loginSucceeded){
            view.loginSuccess();
        }else{
            view.loginError("User not registered");
        }
    }
}

