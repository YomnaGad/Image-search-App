package com.example.android.searchimg.ui.register;

import com.example.android.searchimg.R;
import com.example.android.searchimg.data.DataManager;

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

    public void onRegisterClicked() {

        String username = view.getUsername();
        if(username.isEmpty()){
            view.showUsernameError(R.string.username_error);
            return;
        }


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

        String confirmPassword = view.getConfirmPassword();
        if(confirmPassword.isEmpty() ){
            view.showPasswordError(R.string.confirm_password_error);
            return;
        }
        if(!confirmPassword.equals(password)){
            view.showPasswordMatchError(R.string.confirm_password_error_match);
            return;
        }


        boolean registerSucceeded = dataManager.Register(username, email, password, confirmPassword);
        if(registerSucceeded){
            view.startMainActivity();
            return;
        }
        else if(!registerSucceeded) {
            view.showRegisterError(R.string.register_failed);
        }

    }
}
