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

public class ListenFragment extends BaseFragment {
    private static final String TAG = ListenFragment.class.getSimpleName();
    private static ListenFragment sListenFragment;

    public static synchronized ListenFragment getInstance() {
        if (null == sListenFragment) {
            sListenFragment = new ListenFragment();
        }
        return sListenFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.user_guide_page, container,false);
        return contentView;
    }
}
