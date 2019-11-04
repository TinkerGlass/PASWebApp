package com.jaba.webapp.domain.itemsContext;

import  com.jaba.webapp.domain.item.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumContext {
    private static List<Album> albums;

    public AlbumContext(){
        albums = new ArrayList<Album>();
    }

    public List<Album> getAllAlbums(){
        return albums;
    }


    public Album getAlbum(int id){
        return albums.get(id);
    }

    public Album getAlbum(String title){
        for(int i = 0; i < albums.size(); i++){
            if(albums.get(i).getTitle() == title){
                return albums.get(i);
            }
        }
        return null;
    }

    public void insertAlbum(Album album){
        albums.add(album);
    }
}
