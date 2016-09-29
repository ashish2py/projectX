package com.dotorbit.projectell.models;

/**
 * Created by Ashish on 29/09/16.
 */
public class Question {


    public String title;
    public String description;
    public String answer;
    public String content;
    public String type;


    public Question(){
        super();
    }

    public Question(String title, String description, String answer, String content, String type){
        super();
        this.title = title;
        this.description = description;
        this.answer = answer;
        this.content = content;
        this.type = type;
    }
}
