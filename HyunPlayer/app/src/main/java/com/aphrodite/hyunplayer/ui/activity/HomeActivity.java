package com.aphrodite.hyunplayer.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.aphrodite.hyunplayer.R;
import com.aphrodite.hyunplayer.config.HyApplication;
import com.aphrodite.hyunplayer.config.SharePrefManager;
import com.aphrodite.hyunplayer.ui.adapter.SlideHomePagerAdapter;
import com.aphrodite.hyunplayer.ui.adapter.UserGuideAdapter;
import com.aphrodite.hyunplayer.ui.base.BaseActivity;
import com.aphrodite.hyunplayer.ui.base.BaseFragment;
import com.aphrodite.hyunplayer.ui.fragment.ListenFragment;
import com.aphrodite.hyunplayer.ui.fragment.SingFragment;
import com.aphrodite.hyunplayer.ui.fragment.WatchFragment;
import com.aphrodite.hyunplayer.ui.view.slideview.SlidingMenu;
import com.aphrodite.hyunplayer.ui.view.slideview.SlidingMenuListener;
import com.aphrodite.hyunplayer.ui.view.viewpager.SlideMenuViewPager;
import com.aphrodite.hyunplayer.util.MeasureUtils;
import com.viewpagerindicator.IconPageIndicator;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = HomeActivity.class.getSimpleName();
    private RelativeLayout mUserGuideLayout;
    private ViewPager mGuideViewPager;
    private IconPageIndicator mGuideIconPageIndicator;

    private static final int[][] APP_INTRODUCTOR_IMAGES = {{R.drawable.app_introductory_title_1, R
            .drawable.app_introductory_img_1}, {R.drawable.app_introductory_title_2, R
            .drawable.app_introductory_img_2}, {R.drawable.app_introductory_title_3, R
            .drawable.app_introductory_img_3}, {R.drawable.app_introductory_title_4, R
            .drawable.app_introductory_img_4}};
    private static final boolean[] APP_INTRODUCTOR_SIGN_PAGES = {false, false, false, true};

    private UserGuideAdapter mGuideAdapter;
    private SlideHomePagerAdapter mHomePagerAdapter;

    /**
     * Slide menu layout
     */
    private SlidingMenu mSlidingMenu;
    private RelativeLayout mActionbarRL;
    private ImageView mActionbarLogout;
    private LinearLayout mActionbarLL;
    private ImageView mActionbarListen;
    private ImageView mActionbarWatch;
    private ImageView mActionbarSing;
    private ImageView mScrollIndicator;
    private ImageView mActionbarSearch;
    private SlideMenuViewPager mSlideViewPager;
    /**
     * Actionbar button widths
     */
    private int[] mBtnsWidth;
    /**
     * Each tab of width
     */
    private int mIndicatorWidth;
    private int mActionbarWidth;
    private LinearLayout.LayoutParams mCusorParams;
    /**
     * Current indicator loaction
     */
    private int mCurrentIndex = 0;

    private List<BaseFragment> mFragments;
    private ListenFragment mListeneFragment;
    private WatchFragment mWatchFragment;
    private SingFragment mSingFragment;
    /**
     * The index of listen,watch and sing
     */
    private static int sListenIndex = 0;
    private static int sWatchIndex = 1;
    private static int sSingIndex = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        firstLogin();
    }

    private void initView() {
        mSlidingMenu = (SlidingMenu) findViewById(R.id.slide_menu);
        mUserGuideLayout = (RelativeLayout) findViewById(R.id.user_guide);
        mGuideViewPager = (ViewPager) findViewById(R.id.user_guide_viewpager);
        mGuideIconPageIndicator = (IconPageIndicator) findViewById(R.id.user_guide_indicator);

        mActionbarRL = (RelativeLayout) findViewById(R.id.actionbar_rl);
        mActionbarLogout = (ImageView) findViewById(R.id.actionbar_logout);
        mActionbarLL = (LinearLayout) findViewById(R.id.actionbar_ll);
        mActionbarListen = (ImageView) findViewById(R.id.actionbar_listen);
        mActionbarListen.setOnClickListener(this);
        mActionbarWatch = (ImageView) findViewById(R.id.actionbar_watch);
        mActionbarWatch.setOnClickListener(this);
        mActionbarSing = (ImageView) findViewById(R.id.actionbar_sing);
        mActionbarSing.setOnClickListener(this);
        mScrollIndicator = (ImageView) findViewById(R.id.actionbar_horizontal_scroll_indicator);
        mActionbarSearch = (ImageView) findViewById(R.id.actionbar_search);
        mSlideViewPager = (SlideMenuViewPager) findViewById(R.id.home_slide_vp);
        mSlideViewPager.setOnPageChangeListener(new HomePageChangeListenr());

        mActionbarListen.setPressed(true);
        mActionbarWatch.setPressed(false);
        mActionbarSing.setPressed(false);
        mActionbarSearch.setPressed(false);
    }

    private void initData() {
        mSlidingMenu.setSlidingMenuListener(new SlidingMenuListener() {
            @Override
            public void open() {
                mSlideViewPager.setScrollable(false);
            }

            @Override
            public void close() {
                mSlideViewPager.setScrollable(true);
            }
        });

        mListeneFragment = ListenFragment.getInstance();
        mWatchFragment = WatchFragment.getInstance();
        mSingFragment = SingFragment.getInstance();

        mFragments = new ArrayList<BaseFragment>();
        mFragments.add(mListeneFragment);
        mFragments.add(mWatchFragment);
        mFragments.add(mSingFragment);
        mHomePagerAdapter = new SlideHomePagerAdapter(this, getSupportFragmentManager(),
                mFragments);
        mSlideViewPager.setAdapter(mHomePagerAdapter);

        if (null == mBtnsWidth) {
            mBtnsWidth = new int[]{mActionbarListen.getWidth(), mActionbarWatch.getWidth(),
                    mActionbarSing.getWidth()};
        }
        mCusorParams = (LinearLayout.LayoutParams) mScrollIndicator.getLayoutParams();

        initIndicatorWidth();
    }

    /**
     * Initalize indicator width
     */
    private void initIndicatorWidth() {
        mActionbarWidth = MeasureUtils.getInstance(HomeActivity.this).getWidgetWidth
                (mActionbarLL);
        mIndicatorWidth = MeasureUtils.getInstance(HomeActivity.this).getWidgetWidth
                (mActionbarListen);
        mCusorParams = (LinearLayout.LayoutParams) mScrollIndicator.getLayoutParams();
        mCusorParams.width = mIndicatorWidth;
        mScrollIndicator.setLayoutParams(mCusorParams);
    }

    /**
     * Is the first time login
     *
     * @return
     */
    private boolean firstLogin() {
        boolean isFirstLogin = HyApplication.getApp().firstLogin();
        if (isFirstLogin) {
            showGuide();
        }
        return isFirstLogin;
    }

    /**
     * The guide of first login
     */
    private void showGuide() {
        SharePrefManager.putBoolean(SharePrefManager
                .KEY_ACCOUNT_FIRST_LOGIN, false);
        mUserGuideLayout.setVisibility(View.VISIBLE);

        ArrayList<View> guides = new ArrayList<View>();
        for (int i = 0; i < APP_INTRODUCTOR_IMAGES.length; i++) {
            int[] resources = APP_INTRODUCTOR_IMAGES[i];
            View view = getLayoutInflater().inflate(R.layout.user_guide_page, null);
            ImageView title = (ImageView) view.findViewById(R.id.user_guide_title);
            title.setImageResource(resources[0]);
            ImageView img = (ImageView) view.findViewById(R.id.user_guide_image);
            img.setImageResource(resources[1]);
            Button startBtn = (Button) view.findViewById(R.id.user_guide_start_btn);
            if (APP_INTRODUCTOR_SIGN_PAGES[i]) {
                startBtn.setVisibility(View.VISIBLE);
                startBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View v) {
                        mUserGuideLayout.setVisibility(android.view.View.GONE);
                    }
                });
            } else {
                startBtn.setVisibility(View.GONE);
            }
            guides.add(view);
        }
        mGuideAdapter = new UserGuideAdapter(guides);
        mGuideViewPager.setAdapter(mGuideAdapter);

        mGuideIconPageIndicator.setViewPager(mGuideViewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.actionbar_logout:
                break;
            case R.id.actionbar_listen:
                setBarOnClickEvent(R.id.actionbar_listen);
                break;
            case R.id.actionbar_watch:
                setBarOnClickEvent(R.id.actionbar_listen);
                break;
            case R.id.actionbar_sing:
                setBarOnClickEvent(R.id.actionbar_listen);
                break;
            case R.id.actionbar_search:
                break;
        }
    }

    private void setBarOnClickEvent(int id) {
        if (mCurrentIndex == id) {
            return;
        } else {
            if (sWatchIndex == id) {
                mActionbarWatch.setPressed(true);
                mActionbarListen.setPressed(false);
                mActionbarSing.setPressed(false);
            } else if (sSingIndex == id) {
                mActionbarSing.setPressed(true);
                mActionbarListen.setPressed(false);
                mActionbarWatch.setPressed(false);
            } else {
                mActionbarListen.setPressed(true);
                mActionbarWatch.setPressed(false);
                mActionbarSing.setPressed(false);
            }
        }
    }

    private class HomePageChangeListenr implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float offset, int positionOffsetPixels) {
            scrollIndicator(position, offset);
        }

        @Override
        public void onPageSelected(int position) {
            mCurrentIndex = position;
            if (sWatchIndex == mCurrentIndex) {
                mActionbarWatch.setPressed(true);
                mActionbarListen.setPressed(false);
                mActionbarSing.setPressed(false);
            } else if (sSingIndex == mCurrentIndex) {
                mActionbarSing.setPressed(true);
                mActionbarListen.setPressed(false);
                mActionbarWatch.setPressed(false);
            } else {
                mActionbarListen.setPressed(true);
                mActionbarWatch.setPressed(false);
                mActionbarSing.setPressed(false);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        private void scrollIndicator(int position, float offset) {
            int space = (int) ((mActionbarWidth - mIndicatorWidth * 3) * 1.0 / 2);
            int shift = space + mIndicatorWidth;
            /**
             * Mark three pages,respectively:0,1,2
             * 0->1; 1->2; 2->1; 1->0
             */
            if (mCurrentIndex == 0 && position == 0)// 0->1
            {
                mCusorParams.leftMargin = (int) ((offset + mCurrentIndex) * shift);

            } else if (mCurrentIndex == 1 && position == 0) // 1->0
            {
                mCusorParams.leftMargin = (int) ((-(1 - offset) + mCurrentIndex) * shift);

            } else if (mCurrentIndex == 1 && position == 1) // 1->2
            {
                mCusorParams.leftMargin = (int) ((offset + mCurrentIndex * shift));
            } else if (mCurrentIndex == 2 && position == 1) // 2->1
            {
                mCusorParams.leftMargin = (int) ((-(1 - offset) + mCurrentIndex) * shift);
            }
            mScrollIndicator.setLayoutParams(mCusorParams);
        }
    }
}
