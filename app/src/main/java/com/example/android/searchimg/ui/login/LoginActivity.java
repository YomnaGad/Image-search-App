package com.example.android.searchimg.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.searchimg.MainActivity;
import com.example.android.searchimg.R;
import com.example.android.searchimg.data.DataManager;
import com.example.android.searchimg.data.model.User;
import com.example.android.searchimg.ui.register.RegisterActivity;

/**
 * Created by Yomna on 11/21/2016.
 */
public class LoginActivity extends AppCompatActivity implements LoginBaseView, View.OnClickListener{

    private TextInputLayout emailTIL;

    private TextInputEditText emailED;
    private EditText passwordED;

    private Button loginButtonView;
    private TextView registerView;

    private View mainView;

    private LoginPresenter presenter;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

    }

    private void init(){
        user = new User();

        mainView = findViewById(R.id.login_main_view);

        emailTIL = (TextInputLayout) findViewById(R.id.login_emailTIL);
        emailED = (TextInputEditText) findViewById(R.id.login_emailED);

        passwordED = (EditText) findViewById(R.id.password);


        loginButtonView = (Button) findViewById(R.id.btnLogin);
        registerView = (TextView) findViewById(R.id.link_to_register);

        loginButtonView.setOnClickListener(this);

        registerView.setOnClickListener(this);

        presenter = new LoginPresenter(this, new DataManager());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                user.setMail(emailED.getText().toString());
                user.setPassword(passwordED.getText().toString());

                presenter.login(user);
                break;
            case R.id.link_to_register:
                startActivity(RegisterActivity.getStartIntent(LoginActivity.this));
                break;
        }
    }

    @Override
    public void loginSuccess() {

        //start main activity
        Intent i = MainActivity.getStartIntent(LoginActivity.this);
        // start new activity is considered as a new task so define flag for it
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //set flag no history no return for stacked activities
        i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(i);
        //destroy current activity
        finish();
    }

    @Override
    public void loginError(String e) {
        Snackbar.make(mainView, e, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void loginComplete() {

    }
}

