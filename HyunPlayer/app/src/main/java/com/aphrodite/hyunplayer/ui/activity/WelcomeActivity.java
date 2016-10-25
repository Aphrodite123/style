package com.aphrodite.hyunplayer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.aphrodite.hyunplayer.HomeActivity;
import com.aphrodite.hyunplayer.R;
import com.aphrodite.hyunplayer.config.BaseConfig;
import com.aphrodite.hyunplayer.config.HyApplication;
import com.aphrodite.hyunplayer.ui.base.BaseActivity;
//wanglin

/**
 * Created by Aphrodite on 2016/9/13.
 */
public class WelcomeActivity extends BaseActivity {
    private static final String TAG = WelcomeActivity.class.getSimpleName();
    private TextView mCountdown;
    private SkipBtnClickListener mSkipBtnClickListener = null;

    private int mCount = 5;
    private static final int TIMER = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_advert);
        intView();
        enterHomeActivity();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BaseConfig.COUNT_DOWN_READY: {
                    doCountDown();
                    if (mCount > 0) {
                        mHandler.sendEmptyMessageDelayed(BaseConfig.COUNT_DOWN_READY, TIMER);
                    } else {
                        mHandler.sendEmptyMessageDelayed(BaseConfig.COUNT_DOWN_END, TIMER);
                    }
                    break;
                }
                case BaseConfig.COUNT_DOWN_END: {
                    enterHomeActivity();
                    break;
                }
            }
        }
    };

    private void enterHomeActivity() {
        Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    //Count down and update layout
    private void doCountDown() {
        mCount--;
        String str = String.format(getResources().getString(R.string.welcome_advert_countdown),
                mCount);
        mCountdown.setText(str);
    }

    private void intView() {
        mCountdown = (TextView) findViewById(R.id.welcome_advert_countdown);
        boolean isFirstLogin = HyApplication.getApp().firstLogin();
        if (isFirstLogin) {
            mCountdown.setVisibility(View.GONE);
        } else {
            mCountdown.setVisibility(View.VISIBLE);
        }
        mSkipBtnClickListener = new SkipBtnClickListener();
        mCountdown.setOnClickListener(mSkipBtnClickListener);
        String str = String.format(getResources().getString(R.string.welcome_advert_countdown),
                mCount);
        mCountdown.setText(str);
//        mHandler.sendEmptyMessageDelayed(BaseConfig.COUNT_DOWN_READY, TIMER);
    }

    private class SkipBtnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            enterHomeActivity();
        }
    }
}
