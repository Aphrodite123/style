package com.aphrodite.hyunplayer.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aphrodite.hyunplayer.R;
import com.aphrodite.hyunplayer.ui.base.BaseFragment;

/**
 * Created by Administrator on 2016/11/16.
 */

public class ListenFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = ListenFragment.class.getSimpleName();
    private static ListenFragment sListenFragment;

    private TextView mLike;
    private TextView mPlaylist;
    private TextView mDownload;
    private TextView mRecent;

    /**
     * The index of like,playlist,download and recent
     */
    private static int sLikeIndex = 0;
    private static int sPlaylistIndex = 1;
    private static int sDownloadIndex = 2;
    private static int sRecentIndex = 3;

    private int mCurrentIndex = -1;

    public static synchronized ListenFragment getInstance() {
        if (null == sListenFragment) {
            sListenFragment = new ListenFragment();
        }
        return sListenFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_listen, container, false);
        initView(contentView);
        return contentView;
    }

    private void initView(View v) {
        mLike = (TextView) v.findViewById(R.id.listen_like_info);
        mPlaylist = (TextView) v.findViewById(R.id.listen_playlist_info);
        mDownload = (TextView) v.findViewById(R.id.listen_download_info);
        mRecent = (TextView) v.findViewById(R.id.listen_recent_info);

        mLike.setOnClickListener(this);
        mPlaylist.setOnClickListener(this);
        mDownload.setOnClickListener(this);
        mRecent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.listen_like_info:
                mCurrentIndex = sLikeIndex;
                setBtnFocus(mCurrentIndex);
                break;
            case R.id.listen_playlist_info:
                mCurrentIndex = sPlaylistIndex;
                setBtnFocus(mCurrentIndex);
                break;
            case R.id.listen_download_info:
                mCurrentIndex = sDownloadIndex;
                setBtnFocus(mCurrentIndex);
                break;
            case R.id.listen_recent_info:
                mCurrentIndex = sRecentIndex;
                setBtnFocus(mCurrentIndex);
                break;
            default:
                break;
        }
    }

    private void setBtnFocus(int index) {
        if (index < 0) {
            return;
        }
        if (sLikeIndex == index) {
            mLike.setPressed(true);
            mLike.setTextColor(getResources().getColor(R.color.fragment_listen_navigation_pressed));
            mPlaylist.setPressed(false);
            mPlaylist.setTextColor(Color.WHITE);
            mDownload.setPressed(false);
            mDownload.setTextColor(Color.WHITE);
            mRecent.setPressed(false);
            mRecent.setTextColor(Color.WHITE);
            //TODO
        } else if (sPlaylistIndex == index) {
            mLike.setPressed(false);
            mLike.setTextColor(Color.WHITE);
            mPlaylist.setPressed(true);
            mPlaylist.setTextColor(getResources().getColor(R.color
                    .fragment_listen_navigation_pressed));
            mDownload.setPressed(false);
            mDownload.setTextColor(Color.WHITE);
            mRecent.setPressed(false);
            mRecent.setTextColor(Color.WHITE);
            //TODO
        } else if (sDownloadIndex == index) {
            mLike.setPressed(false);
            mLike.setTextColor(Color.WHITE);
            mPlaylist.setPressed(false);
            mPlaylist.setTextColor(Color.WHITE);
            mDownload.setPressed(true);
            mDownload.setTextColor(getResources().getColor(R.color
                    .fragment_listen_navigation_pressed));
            mRecent.setPressed(false);
            mRecent.setTextColor(Color.WHITE);
            //TODO
        } else {
            mLike.setPressed(false);
            mLike.setTextColor(Color.WHITE);
            mPlaylist.setPressed(false);
            mPlaylist.setTextColor(Color.WHITE);
            mDownload.setPressed(false);
            mDownload.setTextColor(Color.WHITE);
            mRecent.setPressed(true);
            mRecent.setTextColor(getResources().getColor(R.color
                    .fragment_listen_navigation_pressed));
            //TODO
        }
    }
}
