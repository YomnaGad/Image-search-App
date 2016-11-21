package com.example.android.searchimg.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.searchimg.MainActivity;
import com.example.android.searchimg.R;
import com.example.android.searchimg.data.DataManager;

/**
 * Created by Yomna on 11/21/2016.
 */
public class LoginActivity extends AppCompatActivity implements LoginBaseView{
    private EditText emailView;
    private EditText passwordView;
    private Button loginButtonView;
    private LoginPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailView = (EditText) findViewById(R.id.email);
        passwordView = (EditText) findViewById(R.id.password);
        loginButtonView = (Button) findViewById(R.id.btnLogin);
        presenter = new LoginPresenter(this, new DataManager());
        loginButtonView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onLoginClicked(v);
            }
        });

    }

    public void onLoginClicked(View view){

        presenter.onLoginClicked();


    }

    @Override
    public String getEmail() {
        return emailView.getText().toString();
    }

    @Override
    public void showEmailError(int resId) {

        emailView.setError(getString(resId));
    }

    @Override
    public String getPassword() {
        return passwordView.getText().toString();
    }

    @Override
    public void showPasswordError(int resId) {
        passwordView.setError(getString(resId));
    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

    @Override
    public void showLoginError(int resId) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
    }

}

