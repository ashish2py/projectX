package com.developerbyweekend.bunker.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ashish on 29/09/16.
 */
public class Question implements Serializable{

    private String id;
    private ArrayList title;
    private ArrayList<Integer> answer;
    private HashMap options;
    private String type;
    private int score;
    private int selected_option = -1;


    public Question(){
        super();
    }

    public Question(String id, ArrayList title, ArrayList<Integer> answer, HashMap options, String type, int score) {
        this.id = id;
        this.title = title;
        this.answer = answer;
        this.options = options;
        this.type = type;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList getTitle() {
        return title;
    }

    public void setTitle(ArrayList title) {
        this.title = title;
    }

    public ArrayList<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<Integer> answer) {
        this.answer = answer;
    }

    public HashMap getOptions() {
        return options;
    }

    public void setOptions(HashMap options) {
        this.options = options;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSelected_option() {
        return selected_option;
    }

    public void setSelected_option(int selected_option) {
        this.selected_option = selected_option;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", title=" + title +
                ", answer=" + answer +
                ", options=" + options +
                ", type='" + type + '\'' +
                ", score=" + score +
                ", selectedOption="+selected_option+
                '}';
    }
}
