package com.aphrodite.hyunplayer.cache;

import com.aphrodite.hyunplayer.model.Song;

import java.util.List;

/**
 * Created by Aphrodite on 2017/3/19.
 */

public interface ISongCache {
    List<Song> getSongs();

    void updateSong();
}
