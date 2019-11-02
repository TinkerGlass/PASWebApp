package com.jaba.webapp.domain.controllCRUD;

import java.util.List;
import java.util.ArrayList;
import com.jaba.webapp.domain.item.Album;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {

    @Autowired
    AlbumRepository albumRepository;

    public List<Album> getAllPersons() {
        List<Album> persons = new ArrayList<Album>();
        albumRepository.findAll().forEach(person -> persons.add(person));
        return persons;
    }

    public Album getAlbumById(int id){
        return albumRepository.findById(id).get();
    }
}
