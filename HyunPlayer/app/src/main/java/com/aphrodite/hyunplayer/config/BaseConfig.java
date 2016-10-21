package com.aphrodite.hyunplayer.config;

import android.os.Environment;

import java.io.File;

/**
 * Created by Aphrodite on 2016/9/16.
 */
public class BaseConfig {
    public static final boolean DEBUG = true;
    public static final String PACKAGE_NAME = "com.example.aphrodite.hyunplayer";

    public static final String SDCARD_PATH = Environment.getExternalStorageDirectory().getPath() +
            File.separator;
    public static final String ROOT_PATH = SDCARD_PATH + "hyunplayer" + File.separator;

    //log configuration
    public static final String LOG_FILE_PATH = ROOT_PATH + "logs" + File.separator;
    public static final String LOG_FILE_SUFFIX = ".log";


    //activity welcome
    public static final int COUNT_DOWN_READY = 0;
    public static final int COUNT_DOWN_END = 1;


}
