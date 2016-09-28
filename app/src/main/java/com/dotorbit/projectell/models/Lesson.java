package com.dotorbit.projectell.models;

/**
 * Created by Ashish on 28/09/16.
 */
public class Lesson {

    public String title;
    public String description;
    public String assessmentName;
    public String assessmentDescription;
    public String assessmentType;
    public Integer assessmentQuestions;

    public Lesson(){
        super();
    }

    public Lesson(String title, String description,String assessmentName, String assessmentDescription, String assessmentType,
                    Integer assessmentQuestions){
        super();
        this.title = title;
        this.description = description;
        this.assessmentName = assessmentName;
        this.assessmentDescription = assessmentDescription;
        this.assessmentType = assessmentType;
        this.assessmentQuestions = assessmentQuestions;
    }

}
