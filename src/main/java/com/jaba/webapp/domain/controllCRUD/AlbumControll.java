package com.jaba.webapp.domain.controllCRUD;

import java.sql.Date;
import java.util.List;

import com.jaba.webapp.domain.audit.AuditInfo;
import com.jaba.webapp.domain.item.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlbumControll {

    @Autowired
    AlbumService albumService;

    @GetMapping("/albums")
    private List<Album> getAllAlbums(){
        return albumService.getAllPersons();
    }

    @GetMapping("/albums/{id}")
    private Album getPerson(@PathVariable("id") int id) {
        return albumService.getAlbumById(id);
    }

    @GetMapping("/albumstest")
    private void setPerson(){
        albumService.saveAlbum();
    }

}
