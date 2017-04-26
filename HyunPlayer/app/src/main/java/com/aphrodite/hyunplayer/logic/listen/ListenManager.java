package com.aphrodite.hyunplayer.logic.listen;

import com.aphrodite.hyunplayer.config.HyApplication;
import com.aphrodite.hyunplayer.logic.impl.BaseManager;

/**
 * Created by Aphrodite on 2017/4/26.
 */

public class ListenManager extends BaseManager {
    private static final String TAG = ListenManager.class.getSimpleName();
    private static ListenManager sListenManager = null;

    public static synchronized ListenManager getInstance() {
        if (null == sListenManager) {
            sListenManager = new ListenManager();
            sListenManager.init(HyApplication.getHyContext());
        }
        return sListenManager;
    }
}
