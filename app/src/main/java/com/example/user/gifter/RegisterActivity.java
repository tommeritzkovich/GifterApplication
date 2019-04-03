package com.example.user.gifter;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;



import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {


    Intent loginActivity;
    Intent homeIntent;
    Intent loginIntent;
    EditText registerEmail;
    EditText registerName;
    EditText registerPassword;
    EditText registerConfirmPassword;
    CheckBox registerCB;
    ImageButton registerBtn;
    public String name;
    DatabaseReference table_user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Inits
        loginActivity = new Intent(this,LoginActivity.class);
        registerEmail = (EditText) findViewById(R.id.signUpEmail);
        registerPassword = (EditText) findViewById(R.id.signUpPassword);
        registerConfirmPassword = (EditText) findViewById(R.id.signUpConfirmPassword);
        registerName = (EditText)findViewById(R.id.signUpName);
        registerCB = (CheckBox)findViewById(R.id.signUpCheckBox);

        //registerBtn.setClickable(false);
        /*registerCB.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view)
            {
                if (checkCompability())
                {

                    registerBtn.setClickable(true);
                }
                else {

                    registerBtn.setClickable(false);
                }
            }

        });*/



    }

    public void signUpButton(View view) {


        final String email = registerEmail.getText().toString().trim();
        final String password = registerPassword.getText().toString().trim();
        final String confirmPassword = registerConfirmPassword.getText().toString().trim();
        name = registerName.getText().toString().trim();

        if (!isEmpty(email)
                && !isEmpty(password)
                && !isEmpty(confirmPassword)) {
            if (isValidEmail(email)) {
                if (doStringsMatch(password, confirmPassword)) {
                    //Enter register code here
                    registerNewEmail(email, password);
                } else {
                    Toast.makeText(this, "Passwords does not match", Toast.LENGTH_SHORT).show();
                }
            } else {

                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            }

        } else {

            Toast.makeText(this, "Please fill all the required fields", Toast.LENGTH_SHORT).show();
        }
    }



    private void sendVerificationEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Sent verification email", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Coul'dnt send verification email", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }


    private void registerNewEmail(String email, String password) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Sending email verification
                            sendVerificationEmail();


                            //Updating user's display name
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                            FirebaseAuth.getInstance().getCurrentUser().updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Completed display name change", Toast.LENGTH_SHORT).show();

                                    }

                                }

                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegisterActivity.this, "Failed to update profile display name", Toast.LENGTH_SHORT).show();
                                }
                            });

                            FirebaseApp app2 = FirebaseApp.getInstance();
                            FirebaseDatabase database2 = FirebaseDatabase.getInstance(app2);
                            FirebaseAuth auth2 = FirebaseAuth.getInstance(app2);
                            DatabaseReference databaseProfileRef = database2.getReference("profile").child(auth2.getCurrentUser().getUid().toString());
                            ProfileDetails profileDetails = new ProfileDetails(0,0,"");
                            databaseProfileRef.setValue(profileDetails);
                            //Sign out to confirm mail
                            FirebaseAuth.getInstance().signOut();
                            Toast.makeText(RegisterActivity.this, "Registered Succesfully, Please Verify Your Email!", Toast.LENGTH_SHORT).show();
                            startActivity(loginIntent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Unable to register", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        ).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private boolean doStringsMatch(String s1, String s2) {
        return s1.equals(s2);
    }

    private boolean isValidEmail(String email) {
        return email.contains("@");
    }

    private boolean isEmpty(String s) {
        return s.equals("");
    }

    public boolean checkIfACTVIsEmpty(EditText actv) {
        boolean flag = false;
        if (actv.getText().toString().equals(""))
            flag = true;
        return flag;
    }

    public boolean checkCompability() {
        if (!checkIfACTVIsEmpty(registerConfirmPassword)
                && !checkIfACTVIsEmpty(registerEmail)
                && !checkIfACTVIsEmpty(registerPassword) && registerCB.isChecked()) {
            //Toast.makeText(this, "Compatible", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            //Toast.makeText(this, "Not Compatible", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
