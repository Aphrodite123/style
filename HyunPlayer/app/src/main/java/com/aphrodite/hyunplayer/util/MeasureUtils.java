package com.aphrodite.hyunplayer.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by Aphrodite on 2016/10/1.
 */

public class MeasureUtils {
    private Context mContext;
    private static MeasureUtils measureUtils = null;

    private WindowManager manager;
    private DisplayMetrics metrics;

    public MeasureUtils(Context context) {
        this.mContext = context;
        manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
    }

    public static synchronized MeasureUtils getInstance(Context context) {
        if (null == measureUtils) {
            measureUtils = new MeasureUtils(context);
        }
        return measureUtils;
    }

    public int getScreenWidth() {
        return metrics.widthPixels;    //Screen width (dip,exmple: 1dip)
    }

    public int getScreenHeight() {
        return metrics.heightPixels;   //Screen height (dip,exmple: 1dip)
    }

}
