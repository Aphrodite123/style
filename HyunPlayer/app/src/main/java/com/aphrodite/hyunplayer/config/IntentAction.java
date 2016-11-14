package com.aphrodite.hyunplayer.config;

/**
 * Created by Aphrodite on 2016/11/12.
 */

public interface IntentAction {
    public static final String ACTION_PREFIX = BaseConfig.PACKAGE_NAME + ".";

    public interface HomeActivityAction {
        String ACTION = ACTION_PREFIX + "ui.activity.HOME";
    }
}
