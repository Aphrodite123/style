package com.aphrodite.hyunplayer.config;

import android.app.Application;
import android.content.Context;

import com.aphrodite.hyunplayer.util.Logger;

/**
 * Created by Aphrodite on 2016/9/14.
 */
public class HyApplication extends Application {
    private static final String TAG = HyApplication.class.getSimpleName();
    private static HyApplication mHyApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();

        Logger.init(this);

        initSystem(this);
    }

    private void initSystem(Context context) {
        Logger.d(TAG, "Init application...");
        mHyApplication = this;
    }

    public static HyApplication getApp() {
        return mHyApplication;
    }

    public boolean firstLogin() {
        boolean isFirstLogin = SharePrefManager.getBoolean(SharePrefManager
                .KEY_ACCOUNT_FIRST_LOGIN, true);
        return isFirstLogin;
    }

    public static Context getHyContext() {
        return mHyApplication.getApplicationContext();
    }
}
