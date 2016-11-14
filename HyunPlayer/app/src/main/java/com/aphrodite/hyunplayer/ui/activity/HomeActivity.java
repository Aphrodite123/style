package com.aphrodite.hyunplayer.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.aphrodite.hyunplayer.R;
import com.aphrodite.hyunplayer.config.HyApplication;
import com.aphrodite.hyunplayer.config.SharePrefManager;
import com.aphrodite.hyunplayer.ui.adapter.UserGuideAdapter;
import com.aphrodite.hyunplayer.ui.base.BaseActivity;
import com.aphrodite.hyunplayer.ui.view.bar.HeadHorizontalScrollBar;
import com.viewpagerindicator.IconPageIndicator;

import java.util.ArrayList;

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

    /**
     * Horizontal scroll menu
     */
    private HeadHorizontalScrollBar mScrollMenu;

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

//        mScrollMenu = (HeadHorizontalScrollBar) findViewById(R.id.slide_menu_hsb);
    }

    private void initData() {
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
}
