package com.dotorbit.projectell.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashish on 29/09/16.
 */
public class Question {

    public String id;
    public String title;
    public String description;
    public String answer;
    public String content;
    public String type;
    public String score;

    public List<Question> questionList = new ArrayList<Question>();
    public List<String> questionIDs = new ArrayList<String>();


    public Question(){
        super();
    }

    public Question(String id, String title, String description, String answer, String content, String type, String score){
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.answer = answer;
        this.content = content;
        this.type = type;
        this.score= score;
    }


    public List<Question> getAssessmentQuestion(){
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());

            JSONArray lessonNodes = obj.getJSONArray("objects");

            for (int i = 0; i < lessonNodes .length(); i++) {
                JSONObject assessmentJSON = lessonNodes.getJSONObject(i);
                JSONArray assessmentQuestions = assessmentJSON.getJSONArray("objects");
                for ( int q = 0; q < assessmentQuestions.length(); q++){

                    JSONObject questionJSON = assessmentQuestions.getJSONObject(q);
                    Log.e("question id", questionJSON.toString());
                    String id = questionJSON .getJSONObject("node").get("id").toString();
                    String title = questionJSON .getJSONObject("node").get("title").toString();
                    String description = questionJSON .getJSONObject("node").get("description").toString();
                    String answer = questionJSON .getJSONObject("node").getJSONObject("type").get("answer").toString();
                    String content= questionJSON .getJSONObject("node").getJSONObject("type").get("content").toString();
                    String type= questionJSON .getJSONObject("node").getJSONObject("type").get("type").toString();
                    String score = questionJSON .getJSONObject("node").getJSONObject("type").get("score").toString();

                    // question creation
                    Question question = new Question(id, title, description, answer, content, type, score);
                    questionList.add(question);
                    questionIDs.add(String.valueOf(q));
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<String> getQuestionIDs(){
        getAssessmentQuestion();
        return questionIDs;
    }

    public List<Question> getQuestionList(){
        getAssessmentQuestion();
        return questionList;
    }


    public String loadJSONFromAsset() {
        String json = null;
        String file = "assets/diagnosis_test.json";
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            Log.d("Questions ", json);
        } catch (IOException ex) {
            ex.printStackTrace();
            Log.e("ERROR", "Go error ", ex);
            return null;
        }
        return json;
    }



}
