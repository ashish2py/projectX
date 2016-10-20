package com.dotorbit.projectell.models;

import java.io.Serializable;

/**
 * Created by sunit on 15/10/16.
 */

public class Video implements Serializable {

    private String url;

    public Video(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
