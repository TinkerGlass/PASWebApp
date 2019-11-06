package com.jaba.webapp.datacontext.datafiller;

import com.jaba.webapp.domain.item.Album;
import com.jaba.webapp.domain.item.Video;

import java.util.List;

public interface ItemDataFiller {
    public void fillAlbums(List<Album> list);
    public void fillVideos(List<Video> list);
}
