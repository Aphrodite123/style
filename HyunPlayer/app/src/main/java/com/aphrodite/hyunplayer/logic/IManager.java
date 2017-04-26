package com.aphrodite.hyunplayer.logic;

import android.content.Context;
import android.os.Handler;

public interface IManager {
    /**
     * Initialize the manager with application context.
     *
     * @param context
     */
    void init(Context context);

    void deInit();

    /**
     * Add a UI handler.
     *
     * @param handler
     */
    void addHandler(Handler handler);

    /**
     * Remove UI handler.
     *
     * @param handler
     */
    void removeHandler(Handler handler);
}
