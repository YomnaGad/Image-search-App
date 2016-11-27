package com.example.android.searchimg.ui.register;

import com.example.android.searchimg.data.DataManager;
import com.example.android.searchimg.data.model.User;

/**
 * Created by Yomna on 11/22/2016.
 */
public class RegisterPresenter {
    private RegisterBaseView view;
    private DataManager dataManager;
    public RegisterPresenter(RegisterBaseView view, DataManager dataManager) {
        this.view = view;
        this.dataManager = dataManager;

    }

    public void onRegisterClicked(User user) {

        boolean registerSucceeded = dataManager.Register(user.getUsername(), user.getMail(), user.getPassword());

        if (registerSucceeded) {
            view.registerSuccess();
        } else {
            view.registerError("User not registered");
        }
    }


}
