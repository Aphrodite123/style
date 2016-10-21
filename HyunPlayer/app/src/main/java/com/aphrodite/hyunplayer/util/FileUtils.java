package com.aphrodite.hyunplayer.util;

import android.os.Environment;

/**
 * Created by Aphrodite on 2016/9/29.
 */

public class FileUtils {
    /**
     * Determine the disk is mounted correctly
     *
     * @return
     */
    public static boolean isSDCARDMounted() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}
