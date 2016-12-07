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
    //
    private boolean isLike;
    // 歌词位置
    private String lyricPath;
    // 文件路径
    private String filePath;
    // 播放列表的Id集合，它们之间用’$id$’分隔
    private String playerList;
    // 是否是网络音乐
    private boolean isNet;
    // MINE类型
    private String mimeType;
    // 是否是下载完成
    private boolean isDownFinish;

    protected Song(Parcel in) {
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
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
    }
}
