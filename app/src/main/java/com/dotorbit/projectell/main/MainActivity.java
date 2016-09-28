package com.dotorbit.projectell.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.dotorbit.projectell.R;
import com.dotorbit.projectell.adapters.LessonAdapter;
import com.dotorbit.projectell.models.Lesson;

public class MainActivity extends AppCompatActivity {

    ListView lessonListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Lesson lesson_data[] = new Lesson[]{
            new Lesson(R.drawable.ic_notes, "Lesson 1"),
            new Lesson(R.drawable.ic_notes, "Lesson 2"),
            new Lesson(R.drawable.ic_notes, "Lesson 3"),
            new Lesson(R.drawable.ic_notes, "Lesson 4"),
            new Lesson(R.drawable.ic_notes, "Lesson 5"),
            new Lesson(R.drawable.ic_notes, "Lesson 6"),
        };

        lessonListView = (ListView) findViewById(R.id.lessonListView);

        LessonAdapter lessonAdapter;
        lessonAdapter = new LessonAdapter(this, R.layout.item_lesson_resource, lesson_data);
        lessonListView.setAdapter(lessonAdapter);

    }
}
