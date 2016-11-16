package com.aphrodite.hyunplayer.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
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
import com.viewpagerindicator.IconPageIndicator;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {
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
     * Horizontal scroll menu
     */
//    private HeadHorizontalScrollBar mScrollMenu;
    /**
     * slide home layout
     */
    private RelativeLayout mActionbarRL;
    private ImageView mActionbarLogout;
    private ImageView mActionbarListen;
    private ImageView mActionbarWatch;
    private ImageView mActionbarSing;
    private View mScrollIndicator;
    private ImageView mActionbarSearch;
    private ViewPager mHomeViewPager;
    /**
     * actionbar button widths
     */
    private int[] mBtnsWidth;
    /**
     * each tab of width
     */
    private int mIndicatorWidth;
    /**
     * current indicator loaction
     */
    private int mCurIndicatorLocation = 0;

    private List<BaseFragment> mFragments;
    private ListenFragment mListeneFragment;
    private WatchFragment mWatchFragment;
    private SingFragment mSingFragment;

//    private HsbAdapter mHsbAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        firstLogin();
    }

    private void initView() {
        mUserGuideLayout = (RelativeLayout) findViewById(R.id.user_guide);
        mGuideViewPager = (ViewPager) findViewById(R.id.user_guide_viewpager);
        mGuideIconPageIndicator = (IconPageIndicator) findViewById(R.id.user_guide_indicator);

        mActionbarRL = (RelativeLayout) findViewById(R.id.actionbar_rl);
        mActionbarLogout = (ImageView) findViewById(R.id.actionbar_logout);
        mActionbarListen = (ImageView) findViewById(R.id.actionbar_listen);
        mActionbarWatch = (ImageView) findViewById(R.id.actionbar_watch);
        mActionbarSing = (ImageView) findViewById(R.id.actionbar_sing);
        mScrollIndicator = (View) findViewById(R.id.actionbar_horizontal_scroll_indicator);
        mActionbarSearch = (ImageView) findViewById(R.id.actionbar_search);
        mHomeViewPager = (ViewPager) findViewById(R.id.home_slide_vp);
        mHomeViewPager.setOnPageChangeListener(new HomePageChangeListenr());

//        mScrollMenu = (HeadHorizontalScrollBar) findViewById(R.id.slide_menu_hsb);
    }

    /**
     * Initalize indicator width
     */
    private void initIndicatorWidth() {
        ViewGroup.LayoutParams cusorParams = mScrollIndicator.getLayoutParams();
        cusorParams.width = mIndicatorWidth;
        mScrollIndicator.setLayoutParams(cusorParams);
    }

    private void initData() {
        mListeneFragment = ListenFragment.getInstance();
        mWatchFragment = WatchFragment.getInstance();
        mSingFragment = SingFragment.getInstance();
        mFragments.add(mListeneFragment);
        mFragments.add(mWatchFragment);
        mFragments.add(mSingFragment);
        mHomePagerAdapter = new SlideHomePagerAdapter(this, mFragments);
        mHomeViewPager.setAdapter(mHomePagerAdapter);

        if (null == mBtnsWidth) {
            mBtnsWidth = new int[]{mActionbarListen.getWidth(), mActionbarWatch.getWidth(),
                    mActionbarSing.getWidth()};
        }
        mIndicatorWidth = mActionbarListen.getWidth();
        initIndicatorWidth();
//        mHsbAdapter = new HsbAdapter(this);
//        mScrollMenu.setAdapter(mHsbAdapter);

    }

    private boolean firstLogin() {
        boolean isFirstLogin = HyApplication.getApp().firstLogin();
        if (isFirstLogin) {
            showGuide();
        }
        return isFirstLogin;
    }

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

    private class HomePageChangeListenr implements ViewPager.OnPageChangeListener {
        int scrollX;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            scrollX =
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            doIndicatorAnim();
        }
    }

    /**
     * The movement animtion effect
     *
     * @param position
     */
    private void doIndicatorAnim(int curLocation, int desLocation) {
        TranslateAnimation animation = new TranslateAnimation(curLocation, desLocation, 0f, 0f);
        animation.setInterpolator(new LinearInterpolator());
        animation.setFillAfter(true);
        animation.setDuration(100);
        mScrollIndicator.startAnimation(animation);
    }
}
