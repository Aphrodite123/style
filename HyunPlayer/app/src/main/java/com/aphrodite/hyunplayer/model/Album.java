package com.aphrodite.hyunplayer.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-12-08.
 */

public class Album implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String picPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
}
