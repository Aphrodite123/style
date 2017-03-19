package com.aphrodite.hyunplayer.util;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.aphrodite.hyunplayer.config.BaseConfig;
import com.aphrodite.hyunplayer.model.Song;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016-12-07.
 * Support search the specified file
 */

public class FileSearchUtils {
    private static final String TAG = FileSearchUtils.class.getSimpleName();
    private static FileSearchUtils searchUtils = null;

    /**
     * Music file types
     */
    private List<String> musicTypes = new ArrayList<String>();

    public static synchronized FileSearchUtils getInstance() {
        if (null == searchUtils) {
            searchUtils = new FileSearchUtils();
        }
        return searchUtils;
    }

    /**
     * Travelsal search music files
     *
     * @param rootFile
     * @return
     */
    public List<Song> searchMusices(File rootFile) {
        List<Song> songList = new ArrayList<Song>();
        //List all files of the current directory
        File[] files = rootFile.listFiles();
        if (files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    searchMusices(file);
                } else {
                    String name = file.getName().trim();
                    if (TextUtils.isEmpty(name)) {
                        return null;
                    }

                    if (isStandardMusic(name)) {
                        Logger.d(TAG, "Enter searchMusices method, paht of music = " + file
                                .getAbsolutePath());
                        Song song = new Song();
//                        song.setFileName(file.getName());
//                        song.setFilePath(file.getAbsolutePath());
                        songList.add(song);
                    }
                }
            }
        }
        return songList;
    }

    /**
     * Determine whether the standard music files according to the file suffix
     *
     * @param musicName
     * @return
     */
    public boolean isStandardMusic(String musicName) {
        musicTypes = Arrays.asList(BaseConfig.MUSIC_TYPES);
        for (String type : musicTypes) {
            if (musicName.endsWith(type)) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

    /**
     * Access to the media files
     *
     * @param context
     */
    public List<Song> getMediaFiles(Context context) {
        Cursor cursor = null;
        List<Song> songList = new ArrayList<Song>();
        Song song = null;
        try {
            cursor = context.getContentResolver().query(MediaStore.Audio.Media
                    .EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media
                    .DEFAULT_SORT_ORDER);

            int count = cursor.getCount();
            if (count <= 0) {
                Logger.d(TAG, "No media files !");
                return null;
            }

            while (cursor.moveToNext()) {
                Logger.d(TAG, "Enter getAllMediaList mthod,path = " + cursor.getString(cursor
                        .getColumnIndex(MediaStore.Audio.Media.DATA)));

                song = new Song();
                song.setId(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
                song.setTitle(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media
                        .TITLE)));
                song.setAlbum(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media
                        .ALBUM)));
                song.setArtist(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media
                        .ARTIST)));
                song.setPath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media
                        .DATA)));
                song.setName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media
                        .DISPLAY_NAME)));
                song.setReleaseDate(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media
                        .YEAR)));
                song.setLength(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media
                        .DURATION)));
                song.setSize(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media
                        .SIZE)));
                songList.add(song);
            }
        } catch (Exception e) {
            Logger.d(TAG, "Enter getMediaFiles method,Exception:" + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return songList;
    }

}
