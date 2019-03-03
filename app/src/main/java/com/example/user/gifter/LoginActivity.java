package com.example.user.gifter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {


    Intent homeActivity, registerActivity;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseApp.initializeApp(this);




        //Inits
        homeActivity = new Intent(this,HomeActivity.class);
        registerActivity = new Intent(this,RegisterActivity.class);

        try {
            mAuth = FirebaseAuth.getInstance();
        }
        catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }


    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        try {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            updateUI(currentUser);
        }
        catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }




    }

    private void updateUI(FirebaseUser currentUser) {
        //Log the dude in
        try{Toast.makeText(this, currentUser.getUid().toString(), Toast.LENGTH_SHORT).show();}
        catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void loginSignUpButton(View view) {
        startActivity(registerActivity);
    }

    public void loginButton(View view) {
        startActivity(homeActivity);
    }
}
