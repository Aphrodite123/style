package com.aphrodite.hyunplayer.ui.view.extension;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Scaling down view and apply to manual selection tools.
 * Created by Aphrodite on 2017/4/26.
 */

public class DownExpandView extends FrameLayout {

    public DownExpandView(Context context) {
        this(context, null);
    }

    public DownExpandView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DownExpandView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
