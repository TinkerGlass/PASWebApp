package com.jaba.webapp.domain.itemsContext;

import  com.jaba.webapp.domain.item.Album;
import  com.jaba.webapp.domain.item.Video;

import java.util.ArrayList;
import java.util.List;

public class DataContext {
    private static List<Album> albums;
    private static List<Video> videos;

    public DataContext(){
        albums = new ArrayList<Album>();
        videos = new ArrayList<Video>();
    }

    //Albums operations
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


    //Videos operations
    public List<Video> getAllVideos(){
        return videos;
    }

    public Video getVideo(int id){
        return videos.get(id);
    }

    public Video getVideo(String title){
        for(int i = 0; i < videos.size(); i++){
            if(videos.get(i).getTitle() == title){
                return videos.get(i);
            }
        }
        return null;
    }

    public void insertVideo(Video video){
        videos.add(video);
    }
}
