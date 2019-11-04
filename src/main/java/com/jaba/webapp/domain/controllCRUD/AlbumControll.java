package com.jaba.webapp.domain.controllCRUD;

import java.util.Date;
import java.util.List;



import com.jaba.webapp.domain.audit.AuditInfo;
import com.jaba.webapp.domain.item.Album;
import com.jaba.webapp.domain.item.Video;
import com.jaba.webapp.domain.itemsContext.DataContext;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlbumControll {

    DataContext dataContext = new DataContext();


    //Albums
    @GetMapping("/albums")
    private List<Album> getAllAlbums(){
        return dataContext.getAllAlbums();
    }

    @GetMapping("/albums/{id}")
    private String getAlbumById(@PathVariable("id") int id) {
        return dataContext.getAlbum(id).toString();
    }

    @GetMapping("/albumsTitle/{title}")
    private String getAlbum(@PathVariable("title") String title) {
        if(dataContext.getAlbum(title) == null)
            return "error";
        else
            return dataContext.getAlbum(title).toString();
    }

    @GetMapping("/albumstest")
    private void insertAlbum(){
        dataContext.insertAlbum(new Album(1, new AuditInfo(), "title", "author", Album.Genre.CLASSICAL, new Date(), 1));
    }

    @GetMapping("/albums/remove/{id}")
    private void removeAlbum(@PathVariable("id") int id){
        dataContext.removeAlbum(id);
    }

    //Videos
    @GetMapping("/videos")
    private List<Video> getAllVideos(){
        return dataContext.getAllVideos();
    }

    @GetMapping("/videos/{id}")
    private String getVideoById(@PathVariable("id") int id){
        return dataContext.getVideo(id).toString();
    }

    @GetMapping("/videosTitle/{title}")
    private String getVideo(@PathVariable("title") String title){
        if(dataContext.getVideo(title) == null)
            return "error";
        else
            return dataContext.getVideo(title).toString();
    }

    @GetMapping("/videostest")
    private void insertVideo(){
        dataContext.insertVideo(new Video());
    }

    @GetMapping("/videos/remove/{id}")
    private void removeVideo(@PathVariable("id") int id){
        dataContext.removeVideo(id);
    }
}
