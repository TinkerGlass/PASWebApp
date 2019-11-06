package com.jaba.webapp.uglytests.controllCRUD;

import java.util.List;

import com.jaba.webapp.domain.item.Album;
import com.jaba.webapp.domain.item.Video;


import com.jaba.webapp.repository.AlbumRepository;
import com.jaba.webapp.repository.VideoRepository;
import com.jaba.webapp.repository.specification.AlbumSpecification;
import com.jaba.webapp.repository.specification.VideoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UglyTestController {

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    VideoRepository videoRepository;

    //Albums
    @GetMapping("/albums")
    private List<Album> getAllAlbums(){
        return albumRepository.find(AlbumSpecification.all());
    }

    @GetMapping("/albums/{id}")
    private Album getAlbumById(@PathVariable("id") int id) {
        return albumRepository.find(AlbumSpecification.byId(id)).get(0);
    }

    @GetMapping("/albums")
    private List<Album> getAlbum(@RequestParam String title) {
        return albumRepository.find(AlbumSpecification.byTitle(title));
    }

    @GetMapping("/albumstest")
    private void insertAlbum(){
        //dataContext.insertAlbum(new Album(1, new AuditInfo(), "title", "author", Album.Genre.CLASSICAL, new Date(), 1));
    }

    @DeleteMapping("/albums/{id}")
    private void removeAlbum(@PathVariable("id") int id){
        albumRepository.removeItem(albumRepository.find(AlbumSpecification.byId(id)).get(0));
    }

    //Videos
    @GetMapping("/videos")
    private List<Video> getAllVideos(){
        return videoRepository.find(VideoSpecification.all());
    }

    @GetMapping("/videos/{id}")
    private Video getVideoById(@PathVariable("id") int id){
        return videoRepository.find(VideoSpecification.byId(id)).get(0);
    }

    @GetMapping("/videos")
    private List<Video> getVideo(@RequestParam String title){
        return videoRepository.find(VideoSpecification.byTitle(title));
    }

    @GetMapping("/videostest")
    private void insertVideo(){
        //dataContext.insertVideo(new Video());
    }

    @DeleteMapping("/videos/{id}")
    private void removeVideo(@PathVariable("id") int id){
        videoRepository.removeItem(videoRepository.find(VideoSpecification.byId(id)).get(0));
    }
}
