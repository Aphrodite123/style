package com.aphrodite.hyunplayer.ui.view.actionbar;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.aphrodite.hyunplayer.R;
import com.aphrodite.hyunplayer.ui.base.BaseFragment;
import com.aphrodite.hyunplayer.util.MeasureUtils;

import java.util.List;

/**
 * Transverse sliding menu
 * Created by Aphrodite on 2016/10/5.
 */

public class HeadHorizontalScrollBar extends LinearLayout {
    private Context mContext;

    private HorizontalScrollView mScrollView;
    private LinearLayout mLinearLayout;
    private View mIndicator;
    private ViewPager mViewPager;

    private List<View> mNavigationViews;
    private List<BaseFragment> mPageViews;
    private boolean[] mVisitStatus;
    //Weather the sliding
    private boolean mSwiped = true;

    private AbsHsbAdapter mAdapter;

    public HeadHorizontalScrollBar(Context context) {
        this(context, null);
    }

    public HeadHorizontalScrollBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeadHorizontalScrollBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        View v = LayoutInflater.from(context).inflate(R.layout.horizontal_scroll_bar, this, true);
        mScrollView = (HorizontalScrollView) v.findViewById(R.id.horizontal_scroll_hsv);
        mLinearLayout = (LinearLayout) v.findViewById(R.id.horizontal_scroll_menu);
        mIndicator = v.findViewById(R.id.horizontal_scroll_indicator);
        mViewPager = (ViewPager) v.findViewById(R.id.horizontal_scroll_viewpager);
    }

    public void setAdapter(AbsHsbAdapter adapter) {
        this.mAdapter = adapter;
        initView(adapter);
    }

    public void notifyDataSetChanged(AbsHsbAdapter adapter) {
        mLinearLayout.removeAllViews();
        initView(adapter);
    }

    private void initView(AbsHsbAdapter adapter) {
        if (null == adapter) {
            return;
        }
        mNavigationViews = adapter.getNavigationViews();
        mPageViews = adapter.getPageViews();
        if (null != mNavigationViews) {
            mVisitStatus = new boolean[mNavigationViews.size()];
        }
        initNavigationViews(mNavigationViews);
        initPageViews(mPageViews);
    }

    /**
     * Initialize the menu item
     *
     * @param navigationViews
     */
    private void initNavigationViews(final List<View> navigationViews) {
        if (null != navigationViews && 0 != navigationViews.size()) {
            for (int i = 0; i < navigationViews.size(); i++) {
                final int position = i;
                mLinearLayout.addView(navigationViews.get(position));
                navigationViews.get(i).setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(position, mSwiped);
                        moveItemToCenter(navigationViews.get(position));
                        mAdapter.onPageChanged(position, mVisitStatus[position]);
                        mVisitStatus[position] = true;
                    }
                });
            }
        }
    }

    private void moveItemToCenter(View view) {
        int screenWidth = MeasureUtils.getInstance(mContext).getScreenWidth();
        int[] location = new int[2];
        view.getLocationInWindow(location);
        int viewWidth = view.getWidth();
        //遗留问题：绝对坐标值
        mScrollView.smoothScrollTo(location[0] + viewWidth / 2 - screenWidth / 2, 0);
    }

    /**
     * Initialize the page content
     *
     * @param pageViews
     */
    private void initPageViews(List<BaseFragment> pageViews) {
    }
}
