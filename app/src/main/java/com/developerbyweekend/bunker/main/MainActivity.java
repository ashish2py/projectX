package com.developerbyweekend.bunker.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.developerbyweekend.bunker.R;
import com.developerbyweekend.bunker.adapters.LessonAdapter;
import com.developerbyweekend.bunker.utils.LessonJsonParsor;
import com.developerbyweekend.bunker.utils.Tree;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //Set Notification
//        Intent intent = new Intent(MainActivity.this, NotificationReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(
//                MainActivity.this.getApplicationContext(), 234324243, intent, 0);
//
//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                20000, 20000 ,pendingIntent);

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
