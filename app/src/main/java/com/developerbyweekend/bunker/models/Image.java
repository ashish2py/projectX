package com.developerbyweekend.bunker.models;

import java.io.Serializable;

/**
 * Created by sunit on 15/10/16.
 */

public class Image implements Serializable{

    private String url;

    public Image(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
