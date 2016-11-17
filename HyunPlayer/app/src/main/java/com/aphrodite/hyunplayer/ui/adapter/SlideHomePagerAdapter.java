package com.aphrodite.hyunplayer.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.aphrodite.hyunplayer.ui.base.BaseFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/11/16.
 */

public class SlideHomePagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<BaseFragment> mFragments;

    public SlideHomePagerAdapter(Context context, FragmentManager fm, List<BaseFragment>
            fragments) {
        super(fm);
        this.mContext = context;
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return mFragments.indexOf(object);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
