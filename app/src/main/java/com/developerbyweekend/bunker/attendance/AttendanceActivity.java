package com.developerbyweekend.bunker.attendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.developerbyweekend.bunker.R;

public class AttendanceActivity extends AppCompatActivity {

    //Public intent constant
    public static final String INTENT_USER = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_attendance);
    }
}
