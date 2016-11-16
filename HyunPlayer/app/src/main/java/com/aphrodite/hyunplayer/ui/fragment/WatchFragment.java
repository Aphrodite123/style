package com.aphrodite.hyunplayer.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
