package com.developerbyweekend.bunker.study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.developerbyweekend.bunker.R;
import com.developerbyweekend.bunker.main.ProfileActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }


    public void imgNextButtonVew(View view){
        Intent mainIntent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(mainIntent);
    }
}



