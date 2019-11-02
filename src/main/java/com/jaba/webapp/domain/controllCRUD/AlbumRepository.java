package com.jaba.webapp.domain.controllCRUD;

import com.jaba.webapp.domain.item.Album;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Integer>{
}
