package com.aphrodite.hyunplayer.cache.impl;

import com.aphrodite.hyunplayer.cache.ISongCache;
import com.aphrodite.hyunplayer.config.HyApplication;
import com.aphrodite.hyunplayer.model.Song;
import com.aphrodite.hyunplayer.ui.fragment.ListenFragment;
import com.aphrodite.hyunplayer.util.FileSearchUtils;
import com.aphrodite.hyunplayer.util.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aphrodite on 2017/3/19.
 */

public class SongCacheImpl implements ISongCache {
    private static final String TAG = SongCacheImpl.class.getSimpleName();
    private List<Song> songList = new ArrayList<Song>();

    private static SongCacheImpl songCache;

    public static synchronized SongCacheImpl getInstance() {
        if (null == songCache) {
            songCache = new SongCacheImpl();
        }
        return songCache;
    }

    private synchronized void initSongs() {
        Logger.d(TAG, "Enter initSongs method");
        if (null != songList && songList.size() > 0) {
            return;
        }
        this.songList = FileSearchUtils.getInstance().getMediaFiles(HyApplication.getHyContext());
    }

    @Override
    public List<Song> getSongs() {
        Logger.d(TAG, "Enter getSongs method");
        if (null == songList || songList.size() == 0) {
            initSongs();
        }
        return songList;
    }

    @Override
    public void updateSong() {
        Logger.d(TAG, "Enter updateSong method");
    }
}
