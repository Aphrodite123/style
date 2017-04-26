package com.aphrodite.hyunplayer.ui.base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.EditText;

import com.aphrodite.hyunplayer.ui.util.UIUtils;
import com.aphrodite.hyunplayer.util.Logger;

/**
 * Created by Aphrodite on 2016/10/7.
 */

public class BaseFragment extends Fragment {
    private static final String TAG = BaseFragment.class.getSimpleName();
    private boolean onShow = false;

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint() && !onShow) {
            onShow = true;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (onShow) {
            onShow = false;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Logger.d(TAG, "Enter setUserVisibleHint method, isVisibleToUser: " + isVisibleToUser + " " +
                "onShow: " + onShow);
        if (isVisibleToUser && !onShow) {
            onShow = true;
        } else if (!isVisibleToUser && onShow) {
            onShow = false;
        }
    }

    protected void showKeyBoard(EditText editText) {
        editText.clearFocus();
        editText.requestFocus();
        UIUtils.openSoftKeyboard(editText);
    }

    protected void hideKeyBoard() {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        UIUtils.closeSoftKeyboard(activity);
    }
}
