package com.aphrodite.hyunplayer.util;

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
    private static FileSearchUtils searchUtils = null;
    private List<Song> songList = new ArrayList<Song>();
    ;
    /**
     * The types of musics
     */
    private static List<String> musicTypes;
    /**
     * Point of the file suffix
     */
    private static String sPoint = ".";

    public static synchronized FileSearchUtils getInstance() {
        if (null == searchUtils) {
            searchUtils = new FileSearchUtils();
            musicTypes = Arrays.asList(BaseConfig.sMusicType);
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
        //List all files of the current directory
        File[] files = rootFile.listFiles();
        if (files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    searchMusices(file);
                } else {
                    String name = file.getName();
                    String suffix = name.substring(name.lastIndexOf(sPoint));
                    if (null != suffix && musicTypes.contains(suffix)) {
                        Song song = new Song();
                        song.setFileName(file.getName());
                        song.setFilePath(file.getAbsolutePath());
                        songList.add(song);
                    }
                }
            }
        }
        return songList;
    }
}
