package com.aphrodite.hyunplayer.logic.sing;

import com.aphrodite.hyunplayer.config.HyApplication;
import com.aphrodite.hyunplayer.logic.impl.BaseManager;

/**
 * Created by Aphrodite on 2017/4/26.
 */

public class SingManager extends BaseManager {
    private static final String TAG = SingManager.class.getSimpleName();
    private static SingManager singManager = null;

    public static synchronized SingManager getInstance() {
        if (null == singManager) {
            singManager = new SingManager();
            singManager.init(HyApplication.getHyContext());
        }
        return singManager;
    }
}
