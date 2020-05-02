package com.example.finalprojectcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
    }

    /**
     * Starts main activity.
     * @param start - the start button
     */
    public void onClickLaunch(final View start) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
