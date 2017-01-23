package com.example.android.searchimg.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.searchimg.R;
import com.example.android.searchimg.data.DataManager;
import com.example.android.searchimg.data.local.PreferencesHelper;
import com.example.android.searchimg.data.model.User;
import com.example.android.searchimg.ui.home.HomeActivity;
import com.example.android.searchimg.ui.register.RegisterActivity;
import com.example.android.searchimg.utils.GlobalEntities;

/**
 * Created by Yomna on 11/21/2016.
 */
public class LoginActivity extends AppCompatActivity implements LoginBaseView, View.OnClickListener{

    private TextInputLayout usernameTIL;

    private TextInputEditText usernameED;

    private TextInputLayout passwordTIL;

    private TextInputEditText passwordED;


    private Button loginButtonView;
    private TextView registerView;

    private View mainView;

    private LoginPresenter presenter;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       /* DbOpenHelper dbOpenHelper = DbOpenHelper.getInstance(this.getApplicationContext());
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(dbOpenHelper);
        PreferencesHelper preferencesHelper = new PreferencesHelper();
        Service service = Service.Creator.getService();
        DataManager dataManager = DataManager.getInstance(this, service, databaseHelper, preferencesHelper);*/
        init();

    }

    private void init(){
        user = new User();

        mainView = findViewById(R.id.login_main_view);

        usernameTIL = (TextInputLayout) findViewById(R.id.login_usernameTIL);
        usernameED = (TextInputEditText) findViewById(R.id.login_usernameED);

        passwordTIL = (TextInputLayout) findViewById(R.id.login_passwordTIL);
        passwordED = (TextInputEditText) findViewById(R.id.login_passwordlED);



        loginButtonView = (Button) findViewById(R.id.btnLogin);
        registerView = (TextView) findViewById(R.id.link_to_register);

        loginButtonView.setOnClickListener(this);

        registerView.setOnClickListener(this);

        presenter = new LoginPresenter(this, DataManager.getInstance(null, null,null,null));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                String usernameTemp = usernameED.getText().toString().replaceAll(" ", "");
                user.setUsername(usernameTemp);
                String passwordTemp = passwordED.getText().toString().replaceAll(" ", "");
                user.setPassword(passwordTemp);

                presenter.login(user);

                break;
            case R.id.link_to_register:
                startActivity(RegisterActivity.getStartIntent(LoginActivity.this));
                break;
        }
    }

    @Override
    public void loginSuccess(User user) {

        PreferencesHelper.saveToPrefs(this, GlobalEntities.USER_LOGGED_IN_TAG, GlobalEntities.SUCCESS_TAG);
        PreferencesHelper.saveToPrefs(this, GlobalEntities.USERNAME_TAG, user.getUsername());
        PreferencesHelper.saveToPrefs(this, GlobalEntities.Password_TAG, user.getPassword());
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);

        //start main activity
      /*  Intent i = HomeActivity.getStartIntent(LoginActivity.this);
        // start new activity is considered as a new task so define flag for it
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //set flag no history no return for stacked activities
        i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(i);
        //destroy current activity
        finish();*/
    }

    @Override
    public void loginError(String e) {

        Snackbar.make(mainView, e, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void loginComplete() {

    }
}

