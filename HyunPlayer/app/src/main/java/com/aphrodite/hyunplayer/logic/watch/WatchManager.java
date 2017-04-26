package com.aphrodite.hyunplayer.logic.watch;

import com.aphrodite.hyunplayer.config.HyApplication;
import com.aphrodite.hyunplayer.logic.impl.BaseManager;
import com.aphrodite.hyunplayer.logic.sing.SingManager;

/**
 * Created by Aphrodite on 2017/4/26.
 */

public class WatchManager extends BaseManager {
    private static final String TAG = WatchManager.class.getSimpleName();
    private static WatchManager sWatchManager = null;

    public static synchronized WatchManager getInstance() {
        if (null == sWatchManager) {
            sWatchManager = new WatchManager();
            sWatchManager.init(HyApplication.getHyContext());
        }
        return sWatchManager;
    }
}
