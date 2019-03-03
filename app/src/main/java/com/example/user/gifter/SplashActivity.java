package com.example.user.gifter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SplashActivity extends AppCompatActivity {


    Intent homeActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        //Inits
        homeActivity = new Intent(this, LoginActivity.class);
    }

    public void splashButton(View view) {
        startActivity(homeActivity);
    }
}
