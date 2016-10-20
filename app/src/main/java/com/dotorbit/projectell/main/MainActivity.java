package com.dotorbit.projectell.main;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.dotorbit.projectell.R;
import com.dotorbit.projectell.adapters.LessonAdapter;
import com.dotorbit.projectell.models.Assessment;
import com.dotorbit.projectell.models.Lesson;
import com.dotorbit.projectell.receivers.NotificationReceiver;
import com.dotorbit.projectell.utils.LessonJsonParsor;
import com.dotorbit.projectell.utils.Tree;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set Notification
        Intent intent = new Intent(MainActivity.this, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                MainActivity.this.getApplicationContext(), 234324243, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                20000, 20000 ,pendingIntent);

        try{
             Tree digLesson = LessonJsonParsor.parseJson("diagnosis_test.json",MainActivity.this);

            //Loop for many
            ArrayList lessonlist = new ArrayList();
            lessonlist.add(digLesson.getRootnode());

            //Set Adapter
            LessonAdapter lessonAdapter = new LessonAdapter(MainActivity.this, R.layout.item_lesson_resource, lessonlist);

            ((ListView) findViewById(R.id.lessonListView)).setAdapter(lessonAdapter);

        }catch (Exception e){
            Toast.makeText(MainActivity.this,"Error: "+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}
