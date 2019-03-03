package com.example.user.gifter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {


    Intent homeActivity, registerActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        //Inits
        homeActivity = new Intent(this,HomeActivity.class);
        registerActivity = new Intent(this,RegisterActivity.class);
    }

    public void loginSignUpButton(View view) {
        startActivity(registerActivity);
    }

    public void loginButton(View view) {
        startActivity(homeActivity);
    }
}
