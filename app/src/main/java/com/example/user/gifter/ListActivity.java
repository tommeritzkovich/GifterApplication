package com.example.user.gifter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ListActivity extends AppCompatActivity {

    Intent homeActivity, findActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Inits

        homeActivity = new Intent(this, HomeActivity.class);
        findActivity = new Intent(this, FindActivity.class);


    }

    public void homeOnClick(View view) {
        startActivity(homeActivity);
    }

    public void findOnClick(View view) {
        startActivity(findActivity);
    }
}
