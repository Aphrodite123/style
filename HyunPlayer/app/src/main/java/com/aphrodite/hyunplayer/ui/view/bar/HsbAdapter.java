package com.aphrodite.hyunplayer.ui.view.bar;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.aphrodite.hyunplayer.R;
import com.aphrodite.hyunplayer.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aphrodite on 2016/10/16.
 */

public class HsbAdapter extends AbsHsbAdapter {
    private Context mContext;

    private int[] menus;

    public HsbAdapter(Context context) {
        this.mContext = context;
        menus = new int[]{R.string.menus_recommended, R.string.menus_tv, R.string.menus_movie,
                R.string.menus_variety, R.string.menus_cartoon, R.string.menus_infomation, R
                .string.menus_entertainment, R.string.menus_funny, R.string.menus_child, R.string
                .menus_original};
    }

    @Override
    public List<View> getNavigationViews() {
        List<View> views = new ArrayList<View>();
        if (null != menus) for (int i = 0; i < menus.length; i++) {
            TextView view = (TextView) LayoutInflater.from(mContext).inflate(R.layout
                    .layout_navigation_item, null);
            view.setText(mContext.getResources().getString(menus[i]));
            views.add(view);
        }
        return views;
    }

    @Override
    public List<BaseFragment> getPageViews() {
        return null;
    }

    @Override
    public void onPageChanged(int position, boolean visitStatus) {

    }
}
