package com.aphrodite.hyunplayer.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.aphrodite.hyunplayer.ui.base.BaseFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/11/16.
 */

public class SlideHomePagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<BaseFragment> mFragments;

    public SlideHomePagerAdapter(Context context, List<BaseFragment> fragments) {
        this.mContext = context;
        this.mFragments = fragments;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (null != mFragments) {
            count = mFragments.size();
        }
        return count;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return mFragments.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
