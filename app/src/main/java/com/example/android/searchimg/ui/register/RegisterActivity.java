package com.example.android.searchimg.ui.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.android.searchimg.R;
import com.example.android.searchimg.data.DataManager;
import com.example.android.searchimg.data.model.User;
import com.example.android.searchimg.ui.home.HomeActivity;

/**
 * Created by Yomna on 11/22/2016.
 */
public class RegisterActivity extends AppCompatActivity implements RegisterBaseView, View.OnClickListener {

    View mainView;
    private TextInputLayout usernameTIL;

    private TextInputEditText usernameED;

    private  TextInputLayout firstNameTIL;

    private  TextInputEditText firstNameED;

    private  TextInputLayout lastNameTIL;

    private  TextInputEditText lastNameED;

    private TextInputLayout emailTIL;

    private TextInputEditText emailED;

    private TextInputLayout passwordTIL;

    private TextInputEditText passwordED;

    private TextInputLayout ConfirmPasswordTIL;

    private TextInputEditText ConfirmPasswordED;

    private Button registerButtonView;
    private RegisterPresenter presenter;
    User user ;
    String temp = "";
    public static Intent getStartIntent(Context context){
        return new Intent(context, RegisterActivity.class);
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
       /* DbOpenHelper dbOpenHelper = DbOpenHelper.getInstance(this.getApplicationContext());
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(dbOpenHelper);
        PreferencesHelper preferencesHelper = new PreferencesHelper();
        Service service = Service.Creator.getService();
        DataManager dataManager = DataManager.getInstance(this, service, databaseHelper, preferencesHelper);*/

        init();


    }

    public void init(){
        user = new User();

        usernameTIL = (TextInputLayout) findViewById(R.id.register_usernameTIL);
        usernameED = (TextInputEditText) findViewById(R.id.register_usernameED);

        firstNameTIL = (TextInputLayout) findViewById(R.id.register_firstNameTIL);
        firstNameED = (TextInputEditText) findViewById(R.id.register_firstNameED);

        lastNameTIL = (TextInputLayout) findViewById(R.id.register_lastNameTIL);
        lastNameED = (TextInputEditText) findViewById(R.id.register_lastNameED);

        emailTIL = (TextInputLayout) findViewById(R.id.register_emailTIL);
        emailED = (TextInputEditText)findViewById(R.id.register_emailED);

        passwordTIL = (TextInputLayout) findViewById(R.id.register_passwordTIL);
        passwordED = (TextInputEditText) findViewById(R.id.register_passwordlED);

        ConfirmPasswordTIL =(TextInputLayout) findViewById(R.id.register_confirm_passwordTIL);
        ConfirmPasswordED = (TextInputEditText) findViewById(R.id.register_confirm_passwordlED);

        registerButtonView = (Button) findViewById(R.id.btnRegister);
        presenter = new RegisterPresenter(this, DataManager.getInstance(null, null,null,null));
        registerButtonView.setOnClickListener( this);
    }

    public String getConfirmPassword() {
        return ConfirmPasswordED.getText().toString();
    }

    public void showPasswordMatchError(int resId) {
        if(!getConfirmPassword().equals(passwordED.getText().toString())){
            ConfirmPasswordED.setError(getString(resId));
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegister:
                temp = firstNameED.getText().toString().replaceAll(" ", "");
                user.setFirstName(temp);
                Log.i("FIrst name",firstNameED.getText().toString() );

                temp = lastNameED.getText().toString().replaceAll(" ", "");
                user.setLastName(temp);
                Log.i("Last name",lastNameED.getText().toString() );

                temp = usernameED.getText().toString().replaceAll(" ", "");
                user.setUsername(temp);
                Log.i("username",usernameED.getText().toString() );

                temp = emailED.getText().toString().replaceAll(" ", "");
                user.setMail(temp);
                Log.i("emailED",emailED.getText().toString() );

                temp = passwordED.getText().toString().replaceAll(" ", "");
                user.setPassword(temp);
                Log.i("passwordED",passwordED.getText().toString() );
                if(!ConfirmPasswordED.getText().toString().equals(user.getPassword()))
                    showPasswordMatchError(R.string.confirm_password_error_match);
                else
                    presenter.onRegisterClicked(user);
                break;

        }

    }

    @Override
    public void registerSuccess(User user) {
        //start main activity
        Intent i = HomeActivity.getStartIntent(RegisterActivity.this);
        // start new activity is considered as a new task so define flag for it
       // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // start new activity is considered as a new task so define flag for it
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //set flag no history no return for stacked activities
        i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(i);
        //destroy current activity
        finish();
    }

    @Override
    public void registerError(String e) {

        Snackbar.make(mainView, e, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void registerComplete() {

    }
}
