package com.dotorbit.projectell.study;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dotorbit.projectell.R;
import com.dotorbit.projectell.main.MainActivity;
import com.dotorbit.projectell.main.ProfileActivity;

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



