package com.dotorbit.projectell.main;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dotorbit.projectell.R;
import com.dotorbit.projectell.main.MainActivity;

public class SplashScreen extends AppCompatActivity {

    Handler activityHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        activityHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
            }
        }, 1000);
    }
}
