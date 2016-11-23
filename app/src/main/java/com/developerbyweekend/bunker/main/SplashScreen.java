package com.developerbyweekend.bunker.main;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.developerbyweekend.bunker.R;
import com.developerbyweekend.bunker.accounts.LoginActivity;
import com.developerbyweekend.bunker.accounts.RegistrationActivity;
import com.developerbyweekend.bunker.api.APIService;

public class SplashScreen extends AppCompatActivity {

    Handler activityHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //init API Service
        APIService.init(getString(R.string.BACKEND_URL));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        TextView headerTxt = (TextView) findViewById(R.id.productIdTxt);
        Animation hyperspaceJump = AnimationUtils.loadAnimation(this, R.anim.abc_slide_in_top);
        headerTxt.startAnimation(hyperspaceJump);

        TextView txtLogin = (TextView) findViewById(R.id.txtLogin);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(mainIntent);
            }
        });

        TextView txtSignup= (TextView) findViewById(R.id.txtSignup);
        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(mainIntent);
            }
        });


//        activityHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(mainIntent);
//            }
//        }, 1000);
    }
}
