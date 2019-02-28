package com.example.user.gifter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FindActivity extends AppCompatActivity {

    Intent homeActivity, listActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        //inite
        homeActivity = new Intent(this, HomeActivity.class);
        listActivity = new Intent(this, ListActivity.class);


    }

    public void homeOnClick(View view) {
        startActivity(homeActivity);
    }

    public void listOnClick(View view) {
        startActivity(listActivity);
    }
}
