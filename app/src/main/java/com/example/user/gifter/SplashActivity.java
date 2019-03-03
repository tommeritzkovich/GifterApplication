package com.example.user.gifter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.FirebaseApp;

public class SplashActivity extends AppCompatActivity {


    Intent homeActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FirebaseApp.initializeApp(this);

        //Inits
        homeActivity = new Intent(this, LoginActivity.class);
    }

    public void splashButton(View view) {
        startActivity(homeActivity);
    }
}
