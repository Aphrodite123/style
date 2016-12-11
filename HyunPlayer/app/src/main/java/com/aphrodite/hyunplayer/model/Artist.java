package com.aphrodite.hyunplayer.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-12-08.
 */

public class Artist implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    //Singer name
    private String name;
    //Picture of singer
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
