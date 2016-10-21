package com.aphrodite.hyunplayer.config;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Aphrodite on 2016/9/17.
 */
public class SharePrefManager {

    private static final String SHARE_PREF_NAME = "hyunplayer";

    public static final String KEY_ACCOUNT_FIRST_LOGIN = "hyunplayer_account_first_login";

    private static SharedPreferences getPreferences() {
        return HyApplication.getApp().getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
    }

    public static void putString(String key, String values) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(key, values);
        editor.commit();
    }

    public static String getString(String key) {
        String values = getPreferences().getString(key, "");
        return values;
    }

    public static String getString(String key, String def_valuse) {
        String values = getPreferences().getString(key, def_valuse);
        return values;
    }

    public static void putBoolean(String key, boolean values) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(key, values);
        editor.commit();
    }

    public static boolean getBoolean(String key) {
        return getPreferences().getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean def_valuse) {
        return getPreferences().getBoolean(key, def_valuse);
    }

    public static void putInt(String key, int values) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt(key, values);
        editor.commit();
    }

    public static int getInt(String key, int def_valuse) {
        return getPreferences().getInt(key, def_valuse);
    }

    public static void putLong(String key, Long values) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putLong(key, values);
        editor.commit();
    }

    public static long getLong(String key, long def_values) {
        return getPreferences().getLong(key, def_values);
    }
}
