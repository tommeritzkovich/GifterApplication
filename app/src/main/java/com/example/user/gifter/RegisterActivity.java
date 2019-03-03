package com.example.user.gifter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {


    Intent loginActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Inits
        loginActivity = new Intent(this,LoginActivity.class);
    }

    public void signUpButton(View view) {
        startActivity(loginActivity);
        Toast.makeText(this, "Registration Completed!", Toast.LENGTH_SHORT).show();
    }
}
