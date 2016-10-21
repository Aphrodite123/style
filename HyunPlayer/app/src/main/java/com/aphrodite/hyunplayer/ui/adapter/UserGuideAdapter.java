package com.aphrodite.hyunplayer.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.aphrodite.hyunplayer.R;
import com.viewpagerindicator.IconPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Aphrodite on 2016/9/26.
 */

public class UserGuideAdapter extends PagerAdapter implements IconPagerAdapter {
    private ArrayList<View> mViews;

    public UserGuideAdapter(ArrayList<View> views) {
        this.mViews = views;
    }

    /**
     * Create the page for the given position. The adapter is responsible for adding the view to
     * the container given here, although it only must ensure this is done by the time it returns
     * from finishUpdate(ViewGroup).
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager) container).addView(mViews.get(position), 0);
        return mViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(mViews.get(position));
    }

    @Override
    public int getCount() {
        if (mViews == null) {
            return 0;
        }
        return mViews.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    /**
     * Determines whether a page View is associated with a specific key object as returned by
     * instantiateItem(ViewGroup, int). This method is required for a PagerAdapter to function
     * properly.
     *
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getIconResId(int i) {
        return R.drawable.user_guide_pager_indicator;
    }
}
