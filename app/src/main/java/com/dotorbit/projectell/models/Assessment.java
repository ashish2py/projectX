package com.dotorbit.projectell.models;

/**
 * Created by Ashish on 28/09/16.
 */
public class Assessment {

    public String title;
    public String description;
    public String type;
    public String lessonName;
    public String lessonDescription;

    public Assessment(String lessonName, String lessonDescription, String title, String description, String type){
        super();
        this.lessonName = lessonName;
        this.lessonDescription = lessonDescription;
        this.title = title;
        this.description = description;
        this.type = type;
    }
}
