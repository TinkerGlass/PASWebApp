package com.jaba.webapp.datacontext;

import com.jaba.webapp.datacontext.datafiller.ItemDataFiller;
import  com.jaba.webapp.domain.item.Album;
import  com.jaba.webapp.domain.item.Video;
import com.jaba.webapp.domain.user.AdministratorUser;
import com.jaba.webapp.domain.user.ClientUser;
import com.jaba.webapp.domain.user.ResourceManagerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ItemDataContext {
    private List<Album> albums = Collections.synchronizedList(new ArrayList<Album>());
    private List<Video> videos = Collections.synchronizedList(new ArrayList<Video>());

    public ItemDataContext() {
    }

    @Autowired(required = false)
    public ItemDataContext(ItemDataFiller filler) {
        filler.fillAlbums(albums);
        filler.fillVideos(videos);
        System.out.println("AAAA!");
    }

    public List<Album> getAlbums() {
        return albums;
    }
    public List<Video> getVideos() {
        return videos;
    }
}
