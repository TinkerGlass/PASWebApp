package com.jaba.webapp.domain.controllCRUD;

import java.util.Date;
import java.util.List;



import com.jaba.webapp.domain.audit.AuditInfo;
import com.jaba.webapp.domain.item.Album;
import com.jaba.webapp.domain.itemsContext.AlbumContext;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlbumControll {

    AlbumContext albumContext = new AlbumContext();

    @GetMapping("/albums")
    private List<Album> getAllAlbums(){
        return albumContext.getAllAlbums();
    }

    @GetMapping("/albums/{id}")
    private String getPerson(@PathVariable("id") int id) {
        return albumContext.getAlbum(id).toString();
    }

    @GetMapping("/albumstest")
    private void setPerson(){
        albumContext.insertAlbum(new Album(1, new AuditInfo(), "title", "author", Album.Genre.CLASSICAL, new Date(), 1));
    }

}
