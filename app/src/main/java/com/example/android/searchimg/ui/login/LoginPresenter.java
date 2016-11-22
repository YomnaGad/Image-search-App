package com.example.android.searchimg.ui.login;

import com.example.android.searchimg.R;
import com.example.android.searchimg.data.DataManager;

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

    public void onLoginClicked() {
        String email = view.getEmail();
        if(email.isEmpty()){
            view.showEmailError(R.string.email_error);
            return;
        }

        String password = view.getPassword();
        if(password.isEmpty()){
            view.showPasswordError(R.string.password_error);
            return;
        }
        boolean loginSucceeded = dataManager.login(email, password);
        if(loginSucceeded){
            view.startMainActivity();
            return;
        }
        else if(!loginSucceeded) {
            view.showLoginError(R.string.login_failed);
        }

    }
    public void linkToRegister(){
        view.startRegisterActivity();
    }

}

