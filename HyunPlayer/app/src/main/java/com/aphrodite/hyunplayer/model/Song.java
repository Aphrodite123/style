package com.aphrodite.hyunplayer.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016-12-07.
 */

public class Song implements Parcelable {
    private int id;
    // Album
    private Album album;
    // Singer
    private Artist artist;
    //Song name
    private String name;
    // File name
    private String fileName;
    // Network url
    private String netUrl;
    //Playing time
    private int durationTime;
    // File size
    private int size;
    //Whether love
    private boolean isLike;
    // Lyric path
    private String lyricPath;
    // File path
    private String filePath;
    // Playlist collection of ID
    private String playerList;
    // Whether network music
    private boolean isNet;
    // MINE type
    private String mineType;
    // Whether the download is complete
    private boolean isDownFinish;

    public Song() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getNetUrl() {
        return netUrl;
    }

    public void setNetUrl(String netUrl) {
        this.netUrl = netUrl;
    }

    public int getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(int durationTime) {
        this.durationTime = durationTime;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public String getLyricPath() {
        return lyricPath;
    }

    public void setLyricPath(String lyricPath) {
        this.lyricPath = lyricPath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getPlayerList() {
        return playerList;
    }

    public void setPlayerList(String playerList) {
        this.playerList = playerList;
    }

    public boolean isNet() {
        return isNet;
    }

    public void setNet(boolean net) {
        isNet = net;
    }

    public String getMineType() {
        return mineType;
    }

    public void setMineType(String mineType) {
        this.mineType = mineType;
    }

    public boolean isDownFinish() {
        return isDownFinish;
    }

    public void setDownFinish(boolean downFinish) {
        isDownFinish = downFinish;
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel source) {
            Song song = new Song();
            song.id = source.readInt();
            song.album = (Album) source.readSerializable();
            song.artist = (Artist) source.readSerializable();
            song.name = source.readString();
            song.fileName = source.readString();
            song.netUrl = source.readString();
            song.durationTime = source.readInt();
            song.size = source.readInt();
            boolean[] bools = new boolean[3];
            source.readBooleanArray(bools);
            song.isLike = bools[0];
            song.isNet = bools[1];
            song.isDownFinish = bools[2];
            song.lyricPath = source.readString();
            song.filePath = source.readString();
            song.playerList = source.readString();
            song.mineType = source.readString();
            return song;
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeSerializable(album);
        dest.writeSerializable(artist);
        dest.writeString(name);
        dest.writeString(fileName);
        dest.writeString(netUrl);
        dest.writeInt(durationTime);
        dest.writeInt(size);
        dest.writeBooleanArray(new boolean[]{isLike, isNet, isDownFinish});
        dest.writeString(lyricPath);
        dest.writeString(filePath);
        dest.writeString(playerList);
        dest.writeString(mineType);
    }
}
