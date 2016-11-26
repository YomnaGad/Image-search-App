package com.example.android.searchimg.ui.register;

import android.content.Context;
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
 * Created by Yomna on 11/22/2016.
 */
public class RegisterActivity extends AppCompatActivity implements RegisterBaseView {

    private EditText username;
    private EditText emailView;
    private EditText passwordView;
    private EditText confirmPasswordView;
    private Button registerButtonView;
    private RegisterPresenter presenter;

    public static Intent getStartIntent(Context context){
        return new Intent(context, RegisterActivity.class);
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        username = (EditText)findViewById(R.id.Username);
        emailView = (EditText)findViewById(R.id.email);
        passwordView = (EditText)findViewById(R.id.password);
        confirmPasswordView = (EditText)findViewById(R.id.confirmPassword);
        registerButtonView = (Button) findViewById(R.id.btnRegister);
        presenter = new RegisterPresenter(this, new DataManager());

        registerButtonView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onRegisterClicked(v);
            }
        });
    }


    public void onRegisterClicked(View view){

        presenter.onRegisterClicked();

    }

    @Override
    public String getUsername() {
        return username.getText().toString();
    }

    @Override
    public void showUsernameError(int resId) {
        username.setError(getString(resId));
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
    public String getConfirmPassword() {
        return confirmPasswordView.getText().toString();
    }

    @Override
    public void showConfirmPasswordError(int resId) {
        confirmPasswordView.setError(getString(resId));
    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

    @Override
    public void showRegisterError(int resId) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showPasswordMatchError(int resId) {
        confirmPasswordView.setError(getString(resId));
    }
}
