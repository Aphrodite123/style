package com.aphrodite.hyunplayer.util;

import android.content.Context;

import com.aphrodite.hyunplayer.config.BaseConfig;
import com.github.snowdream.android.util.FilePathGenerator;
import com.github.snowdream.android.util.Log;

/**
 * Created by Aphrodite on 2016/9/29.
 */

public final class Logger {
    private static final int MAX_LOG_FILE_SIZE = 1024 * 1024;//1M
    private static final String LOG_FILE_NAME = "log";
    private static final String GLOBAL_TAG = "Intracircle";

    private static String logDir = null;
    private static boolean sIsInite = false;

    public synchronized static void init(Context context) {
        if (sIsInite) {
            return;
        }
        Log.setEnabled(true);
        Log.setLog2ConsoleEnabled(true);
        Log.setGlobalTag(GLOBAL_TAG);
        if (FileUtils.isSDCARDMounted()) {
            logDir = BaseConfig.LOG_FILE_PATH;
            Log.setFilePathGenerator(new FilePathGenerator.LimitSizeFilePathGenerator(
                    logDir, LOG_FILE_NAME, BaseConfig.LOG_FILE_SUFFIX, MAX_LOG_FILE_SIZE));
            Log.setLog2FileEnabled(true);
        } else {
            Log.setLog2FileEnabled(false);
        }
        sIsInite = true;
    }

    /**
     * Send a DEBUG log message.
     *
     * @param msg The message you would like logged.
     */
    public static void d(String tag, String msg) {
        Log.d(tag, msg);
    }

    /**
     * Send a DEBUG log message.
     */
    public static void d(String msg) {
        Log.d(msg);
    }

    /**
     * Send a DEBUG log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param thr An exception to log
     */
    public static void d(String tag, String msg, Throwable thr) {
        Log.d(tag, msg, thr);
    }

    /**
     * Send a DEBUG log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param thr An exception to log
     */
    public static void d(String msg, Throwable thr) {
        Log.d(msg, thr);
    }

    /**
     * Send a ERROR log message.
     *
     * @param msg The message you would like logged.
     */
    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    /**
     * Send an ERROR log message.
     *
     * @param msg The message you would like logged.
     */
    public static void e(String msg) {
        Log.e(msg);
    }

    /**
     * Send a ERROR log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param thr An exception to log
     */
    public static void e(String tag, String msg, Throwable thr) {
        Log.e(tag, msg, thr);
    }

    /**
     * Send an ERROR log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param thr An exception to log
     */
    public static void e(String msg, Throwable thr) {
        Log.e(msg, thr);
    }

    /**
     * Send a INFO log message.
     *
     * @param msg The message you would like logged.
     */
    public static void i(String tag, String msg) {
        Log.i(tag, msg);
    }

    /**
     * Send an INFO log message.
     *
     * @param msg The message you would like logged.
     */
    public static void i(String msg) {
        Log.i(msg);
    }

    /**
     * Send a INFO log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param thr An exception to log
     */
    public static void i(String tag, String msg, Throwable thr) {
        Log.i(tag, msg, thr);
    }

    /**
     * Send a INFO log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param thr An exception to log
     */
    public static void i(String msg, Throwable thr) {
        Log.i(msg, thr);
    }

    /**
     * Send a VERBOSE log message.
     *
     * @param msg The message you would like logged.
     */
    public static void v(String tag, String msg) {
        Log.v(tag, msg);
    }

    /**
     * Send a VERBOSE log message.
     *
     * @param msg The message you would like logged.
     */
    public static void v(String msg) {
        Log.v(msg);
    }

    /**
     * Send a VERBOSE log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param thr An exception to log
     */
    public static void v(String tag, String msg, Throwable thr) {
        Log.v(tag, msg, thr);
    }

    /**
     * Send a VERBOSE log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param thr An exception to log
     */
    public static void v(String msg, Throwable thr) {
        Log.v(msg, thr);
    }

    /**
     * Send an empty WARN log message and log the exception.
     *
     * @param thr An exception to log
     */
    public static void w(Throwable thr) {
        Log.w(thr);
    }

    /**
     * Send a WARN log message.
     *
     * @param msg The message you would like logged.
     */
    public static void w(String tag, String msg) {
        Log.w(tag, msg);
    }

    /**
     * Send a WARN log message
     *
     * @param msg The message you would like logged.
     */
    public static void w(String msg) {
        Log.w(msg);
    }

    /**
     * Send a WARN log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param thr An exception to log
     */
    public static void w(String tag, String msg, Throwable thr) {
        Log.w(tag, msg, thr);
    }

    /**
     * Send a WARN log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param thr An exception to log
     */
    public static void w(String msg, Throwable thr) {
        Log.w(msg, thr);
    }
}
