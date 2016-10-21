package com.aphrodite.hyunplayer.ui.view.bar;

import android.app.Fragment;
import android.view.View;

import com.aphrodite.hyunplayer.ui.base.BaseFragment;

import java.util.List;

/**
 * Created by Aphrodite on 2016/10/6.
 */

public abstract class AbsHsbAdapter {
    private HeadHorizontalScrollBar mScrollBar;

    //Navigation view
    public abstract List<View> getNavigationViews();

    //Page view
    public abstract List<BaseFragment> getPageViews();

    public abstract void onPageChanged(int position, boolean visitStatus);

    public void setHorizontalScrollBar(HeadHorizontalScrollBar scrollBar) {
        this.mScrollBar = scrollBar;
    }

    public void notifyDataSetChanged() {
        mScrollBar.notifyDataSetChanged(this);
    }
}
