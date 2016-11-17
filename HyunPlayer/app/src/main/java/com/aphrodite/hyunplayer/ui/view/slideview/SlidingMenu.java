package com.aphrodite.hyunplayer.ui.view.slideview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.aphrodite.hyunplayer.R;
import com.aphrodite.hyunplayer.util.MeasureUtils;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by Aphrodite on 2016/10/1.
 */

public class SlidingMenu extends HorizontalScrollView {
    private int mScreenWidth;
    private int mMenuRightPadding;

    private int mMenuWidth;
    private int mHalfMenuWidth;

    private boolean isOpen;

    private boolean mOnce;

    private ViewGroup mMenu;
    private ViewGroup mContent;

    private SlidingMenuListener menuListener;

    public SlidingMenu(Context context) {
        this(context, null, 0);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScreenWidth = MeasureUtils.getInstance(context).getScreenWidth();
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.SlidingMenu, defStyleAttr, 0);
        int n = typedArray.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.SlidingMenu_rightPadding:
                    mMenuRightPadding = typedArray.getDimensionPixelSize(attr, (int) TypedValue
                            .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50f, getResources()
                                    .getDisplayMetrics()));
                    break;
            }
        }
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!mOnce) {
            LinearLayout ll = (LinearLayout) getChildAt(0);
            mMenu = (ViewGroup) ll.getChildAt(0);
            mContent = (ViewGroup) ll.getChildAt(1);

            mMenuWidth = mScreenWidth - mMenuRightPadding;
            mHalfMenuWidth = mMenuWidth / 2;
            mMenu.getLayoutParams().width = mMenuWidth;
            mContent.getLayoutParams().width = mScreenWidth;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            //Hide the menu
            this.scrollTo(mMenuWidth, 0);
            mOnce = true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP: {
                int scrollX = getScrollX();
                //If the display area is greater than the half width of the menu is completely,
                // otherwise hidden
                if (scrollX > mHalfMenuWidth) {
                    this.smoothScrollTo(mMenuWidth, 0);
                    isOpen = false;
                    menuListener.close();
                } else {
                    this.smoothScrollTo(0, 0);
                    isOpen = true;
                    menuListener.open();
                }
            }
            return true;
        }
        return super.onTouchEvent(ev);
    }

    public void openMenu() {
        if (isOpen) {
            return;
        }
        this.smoothScrollTo(0, 0);
        isOpen = true;
        menuListener.open();
    }

    public void closeMenu() {
        if (isOpen) {
            this.smoothScrollTo(mMenuWidth, 0);
            isOpen = false;
            menuListener.close();
        }
    }

    /**
     * Change menu status
     */
    public void toggle() {
        if (isOpen) {
            closeMenu();
        } else {
            openMenu();
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float scale = l * 1.0f / mMenuWidth;
        float leftScale = 1 - 0.3f * scale;
        float rightScale = 0.8f + scale * 0.2f;

        ViewHelper.setScaleX(mMenu, leftScale);
        ViewHelper.setScaleY(mMenu, leftScale);
        ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
        ViewHelper.setTranslationX(mMenu, mMenuWidth * scale * 0.7f);

        ViewHelper.setPivotX(mContent, 0);
        ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
        ViewHelper.setScaleX(mContent, rightScale);
        ViewHelper.setScaleY(mContent, rightScale);
    }

    public void setSlidingMenuListener(SlidingMenuListener menuListener) {
        this.menuListener = menuListener;
    }
}
