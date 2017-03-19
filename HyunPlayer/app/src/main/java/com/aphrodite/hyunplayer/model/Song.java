package com.aphrodite.hyunplayer.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-12-07.
 */

public class Song implements Serializable {
    private int id;

    /**
     * The name of song
     */
    private String title;

    /**
     * The album of song
     */
    private String album;

    /**
     * Singer
     */
    private String artist;

    /**
     * The path of song
     */
    private String path;

    /**
     * The name of song
     */
    private String name;

    /**
     * Song file release date
     */
    private String releaseDate;

    /**
     * The total running time of songs
     */
    private int length;

    /**
     * The size of songs
     */
    private int size;

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
