package com.developerbyweekend.bunker.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.developerbyweekend.bunker.R;
import com.developerbyweekend.bunker.accounts.LoginActivity;
import com.developerbyweekend.bunker.accounts.RegistrationActivity;
import com.developerbyweekend.bunker.attendance.AttendanceActivity;
import com.developerbyweekend.bunker.models.User;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

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
                finish();
            }
        });

        TextView txtSignup= (TextView) findViewById(R.id.txtSignup);
        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

        //If register continue
        User user = User.getUserFromLocal(SplashScreen.this);
        if(user!=null){
            Intent attendanceActvity = new Intent(getApplicationContext(), AttendanceActivity.class);
            startActivity(attendanceActvity);
            finish();
        }

    }
}
