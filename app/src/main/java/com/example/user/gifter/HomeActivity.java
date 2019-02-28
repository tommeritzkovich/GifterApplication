package com.example.user.gifter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    Intent findActivity;
    Intent listActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findActivity = new Intent(this, FindActivity.class);
        listActivity = new Intent(this, ListActivity.class);

    }

    public void findButtonClick(View view) {
        startActivity(findActivity);
    }

    public void listButtonClick(View view) {
        startActivity(listActivity);
    }
}
