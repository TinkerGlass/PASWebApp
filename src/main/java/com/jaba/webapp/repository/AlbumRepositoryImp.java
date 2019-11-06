package com.jaba.webapp.repository;

import com.jaba.webapp.datacontext.ItemDataContext;
import com.jaba.webapp.domain.item.Album;
import com.jaba.webapp.domain.user.User;
import com.jaba.webapp.repository.specification.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AlbumRepositoryImp implements AlbumRepository {

    @Autowired
    private ItemDataContext itemDataContext;

    @Override
    public List<Album> find(Specification<Album> specification) {
        synchronized (itemDataContext.getAlbums()) {
            return itemDataContext.getAlbums().stream()
                    .filter(album -> specification.matches(album))
                    .filter(album -> album.getAuditInfo().getDeletionDate() == null)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public void addItem(Album item, User user) {
        synchronized (itemDataContext.getAlbums()) {
            if (itemDataContext.getAlbums().stream().filter(album -> album.getId() == item.getId()).findAny().isPresent())
                throw new IllegalArgumentException(String.format("Album with ID {} already exists", item.getId()));
            item.onCreate(user);
            itemDataContext.getAlbums().add(item);
        }
    }

    @Override
    public void removeItem(Album item, User user) {
        synchronized (itemDataContext.getAlbums()) {
            for (int i = 0; i < itemDataContext.getAlbums().size(); i++) {
                if(itemDataContext.getAlbums().get(i).getId() == item.getId()) {
                    if(itemDataContext.getAlbums().get(i).getAuditInfo().getDeletionDate() != null)
                        return;
                    itemDataContext.getAlbums().get(i).onDelete(user);
                    return;
                }
            }
        }
    }

    @Override
    public void updateItem(Album item, User user) {
        synchronized (itemDataContext.getAlbums()) {
            for (int i = 0; i < itemDataContext.getAlbums().size(); i++) {
                if(itemDataContext.getAlbums().get(i).getId() == item.getId()) {
                    if(itemDataContext.getAlbums().get(i).getAuditInfo().getDeletionDate() != null)
                        break;
                    item.onUpdate(user);
                    itemDataContext.getAlbums().set(i, item);
                    return;
                }
            }
        }
        throw new IllegalArgumentException(String.format("Album with ID {} doesn't exist", item.getId()));
    }
}
