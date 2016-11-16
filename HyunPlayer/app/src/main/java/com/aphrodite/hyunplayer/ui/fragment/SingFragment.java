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

public class SingFragment extends BaseFragment {
    private static final String TAG = SingFragment.class.getSimpleName();
    private static SingFragment sSingFragment;

    public static synchronized SingFragment getInstance() {
        if (null == sSingFragment) {
            sSingFragment = new SingFragment();
        }
        return sSingFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
