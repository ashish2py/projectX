package com.developerbyweekend.bunker.models;

/**
 * Created by Ashish on 28/09/16.
 */
public class Lesson {

    private String id;
    private String title;
    private String description;

    public Lesson(){
        super();
    }



    public Lesson(String id, String title, String description){
        super();
        this.id=id;
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
