package com.aphrodite.hyunplayer.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aphrodite.hyunplayer.R;
import com.aphrodite.hyunplayer.ui.base.BaseFragment;

/**
 * Created by Administrator on 2016/11/16.
 */

public class WatchFragment extends BaseFragment {
    private static final String TAG = WatchFragment.class.getSimpleName();
    private static WatchFragment sWatchFragment;

    public static synchronized WatchFragment getInstance() {
        if (null == sWatchFragment) {
            sWatchFragment = new WatchFragment();
        }
        return sWatchFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.user_guide_page, container, false);
        return contentView;
    }

    /**
     * Release fragment resource.
     */
    public static void releaseInstance() {
        if (null != sWatchFragment) {
            sWatchFragment = null;
        }
    }
}
