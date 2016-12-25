package com.example.android.searchimg.ui.landing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.searchimg.R;
import com.example.android.searchimg.data.DataManager;
import com.example.android.searchimg.data.local.DatabaseHelper;
import com.example.android.searchimg.data.local.DbOpenHelper;
import com.example.android.searchimg.data.local.PreferencesHelper;
import com.example.android.searchimg.data.remote.Service;
import com.example.android.searchimg.ui.login.LoginActivity;
import com.example.android.searchimg.ui.register.RegisterActivity;

public class LandingActivity extends AppCompatActivity {
    private Button loginBtn;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        loginBtn = (Button) findViewById(R.id.login_btn);
        registerBtn = (Button) findViewById(R.id.register_btn);
        DbOpenHelper dbOpenHelper = DbOpenHelper.getInstance(this.getApplicationContext());
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(dbOpenHelper);
        PreferencesHelper preferencesHelper = new PreferencesHelper();
        Service service = Service.Creator.getService();
        DataManager dataManager = DataManager.getInstance(this, service, databaseHelper, preferencesHelper);

        init();
    }

    public void init(){
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandingActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandingActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
