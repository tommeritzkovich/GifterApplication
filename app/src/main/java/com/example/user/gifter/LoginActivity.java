package com.example.user.gifter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {


    Intent homeActivity, registerActivity;
    private static final String TAG = "";
    private Button btnLogin;
    private Button btnLinkToRegister;
    private CheckBox loginCB;
    private EditText loginEmail;
    private EditText loginPassword;
    private ProgressDialog mDialog;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseApp.initializeApp(this);






        //Inits
        homeActivity = new Intent(this,HomeActivity.class);
        registerActivity = new Intent(this,RegisterActivity.class);
        loginEmail = (EditText)findViewById(R.id.loginEmail);
        loginPassword = (EditText)findViewById(R.id.loginPassword);




        setupFirebaseAuth();



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


        //Sign in credentials
        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();





        //Check if the user filled the credentials
        if (!isEmpty(email) && !isEmpty(password)) {
            //Try to login with the credentials the user inputted
            try {


            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    //Let the user know his credentials was wrong =
                    mDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                }
            });
            }
            catch (Exception ex){
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();}
        }


    }


    private boolean isEmpty(String s) {
        return s.equals("");
    }



/*
-----------------------------------------Firebase Setup------------------------------
 */

    private void setupFirebaseAuth() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                //Check if a user is signed in
                if (user != null) {

                    //In case he is and he verified his Email
                    if (user.isEmailVerified()) {
                        //Let the user know he is now signed in
                        Log.d(TAG, "onAuthStateChanged:signed_in: " + user.getUid());
                        Toast.makeText(LoginActivity.this, "Authenticated with: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                        try {
                            mDialog.dismiss();
                        } catch (NullPointerException e) {
                            Log.e("Error", e.getMessage());
                        }
                        startActivity(homeActivity);
                        finish();
                    } else {

                        //Tell the user to verify his email
                        try {
                            mDialog.dismiss();
                        } catch (NullPointerException e) {
                            Log.e("Error", e.getMessage());
                        }
                        Toast.makeText(LoginActivity.this, "Please check your inbox for verification email", Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                    }

                } else {
                    //User Signed out
                }
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        //FirebaseAuth.getInstance().addAuthStateListener((mAuthListener));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }
}
