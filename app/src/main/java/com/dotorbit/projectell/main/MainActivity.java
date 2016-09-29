package com.dotorbit.projectell.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.dotorbit.projectell.R;
import com.dotorbit.projectell.adapters.LessonAdapter;
import com.dotorbit.projectell.models.Assessment;
import com.dotorbit.projectell.models.Lesson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lessonListView;
    final ArrayList<HashMap<String, String>> lessonAssessmentList = new ArrayList<HashMap<String, String>>();
    final List<Lesson> lessonList = new ArrayList<Lesson>();
    Lesson lesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lessonListView = (ListView) findViewById(R.id.lessonListView);

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());

            final String lessonTitle = obj.getJSONObject("node").get("title").toString();
            final String lessonDesc= obj.getJSONObject("node").get("description").toString();

            JSONArray lessonNodes = obj.getJSONArray("objects");

            for (int i = 0; i < lessonNodes .length(); i++) {

                JSONObject assessmentJSON = lessonNodes.getJSONObject(i);

                JSONArray assessmentQuestions = assessmentJSON.getJSONArray("objects");
                Integer qLength = assessmentQuestions.length();

                String nodeTile = assessmentJSON.getJSONObject("node").get("title").toString();
                String nodeDesc= assessmentJSON.getJSONObject("node").get("description").toString();
                String nodeType= assessmentJSON.getJSONObject("node").get("content_type_name").toString();

                lesson = new Lesson(lessonTitle, lessonDesc, nodeTile, nodeDesc, nodeType, qLength );
                lessonList.add(lesson);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



        LessonAdapter lessonAdapter;
        lessonAdapter = new LessonAdapter(this, R.layout.item_lesson_resource, lessonList);
        lessonListView.setAdapter(lessonAdapter);


    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("diagnosis_test.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
