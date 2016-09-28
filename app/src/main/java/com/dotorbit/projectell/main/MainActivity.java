package com.dotorbit.projectell.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.dotorbit.projectell.R;
import com.dotorbit.projectell.models.Lesson;

public class MainActivity extends AppCompatActivity {

    ListView lessonListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Lesson lesson_data[] = new Lesson[]{
            new Lesson(R.drawable.ic_notes, "Cloudy"),
            new Lesson(R.drawable.ic_notes, "Showers"),
        };

        lessonListView = (ListView) findViewById(R.id.lessonListView);
    }
}
