package com.aphrodite.hyunplayer.logic.impl;

import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import com.aphrodite.hyunplayer.logic.IManager;

public class BaseManager implements IManager {
    @SuppressWarnings("unused")
    private static final String TAG = BaseManager.class.getSimpleName();

    private HandlerThread mHandlerThread;

    /**
     * Application context.
     */
    protected Context mContext;

    protected ManagerHandler mHandler;
    protected boolean inited = false;

    /**
     * UI handler.
     */
    private final List<Handler> mHandlerList = new Vector<Handler>();

    @Override
    public void init(Context context) {
        if (inited) {
            return;
        }
        inited = true;
        this.mContext = context;
        mHandlerThread = new HandlerThread(this.getClass().getSimpleName());
        mHandlerThread.start();
        mHandler = new ManagerHandler(mHandlerThread.getLooper());
    }

    @Override
    public void deInit() {
        if (!inited) {
            return;
        }
        mHandler.sendEmptyMessage(ManagerHandler.QUIT);
        inited = false;
    }

    @Override
    public void addHandler(Handler handler) {
        if (!mHandlerList.contains(handler)) {
            mHandlerList.add(handler);
        }
    }

    @Override
    public void removeHandler(Handler handler) {
        mHandlerList.remove(handler);
    }

    /**
     * Send handler message to UI.
     *
     * @param what
     * @param obj
     */
    public void sendMessage(int what, Object obj) {
        synchronized (mHandlerList) {
            for (Handler handler : mHandlerList) {
                if (obj == null) {
                    handler.sendEmptyMessage(what);
                } else {
                    Message message = new Message();
                    message.what = what;
                    message.obj = obj;
                    handler.sendMessage(message);
                }
            }
        }
    }

    /**
     * Send handler message to UI.
     *
     * @param what
     */
    public void sendEmptyMessage(int what) {
        synchronized (mHandlerList) {
            for (Handler handler : mHandlerList) {
                handler.sendEmptyMessage(what);
            }
        }
    }

    /**
     * Send handler message to UI.
     *
     * @param what
     * @param obj
     * @param delayMillis
     */
    public void sendMessageDelayed(int what, Object obj, long delayMillis) {
        synchronized (mHandlerList) {
            for (Handler handler : mHandlerList) {
                if (obj == null) {
                    handler.sendEmptyMessageDelayed(what, delayMillis);
                } else {
                    Message message = new Message();
                    message.what = what;
                    message.obj = obj;
                    handler.sendMessageDelayed(message, delayMillis);
                }
            }
        }
    }

    /**
     * Send handler message to UI.
     *
     * @param what
     * @param delayMillis
     */
    public void sendEmptyMessageDelayed(int what, long delayMillis) {
        synchronized (mHandlerList) {
            for (Handler handler : mHandlerList) {
                handler.sendEmptyMessageDelayed(what, delayMillis);
            }
        }
    }

    protected class ManagerHandler extends Handler {
        public static final int QUIT = -1;

        public ManagerHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == QUIT) {
                mHandlerThread.quit();
                return;
            }
            super.handleMessage(msg);
        }
    }
}
