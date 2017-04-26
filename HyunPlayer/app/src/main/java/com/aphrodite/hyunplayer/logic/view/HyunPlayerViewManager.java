package com.aphrodite.hyunplayer.logic.view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.aphrodite.hyunplayer.R;
import com.aphrodite.hyunplayer.config.HyApplication;
import com.aphrodite.hyunplayer.util.FileSearchUtils;

/**
 * Created by Aphrodite on 2017/4/25.
 */

public class HyunPlayerViewManager {
    private static final String TAG = HyunPlayerViewManager.class.getSimpleName();
    private static HyunPlayerViewManager sHyunPlayerManager = null;

    public static synchronized HyunPlayerViewManager getInstance() {
        if (null == sHyunPlayerManager) {
            sHyunPlayerManager = new HyunPlayerViewManager();
        }
        return sHyunPlayerManager;
    }

    /**
     * Set the status bar of immersion.
     *
     * @param activity
     */
    public void setStatusBarStyle(Activity activity) {
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //Transparent status bar.
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //Transparent navigation bar.
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
}
