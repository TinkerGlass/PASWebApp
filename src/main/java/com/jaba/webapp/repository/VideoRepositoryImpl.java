package com.jaba.webapp.repository;

import com.jaba.webapp.datacontext.ItemDataContext;
import com.jaba.webapp.domain.item.Video;
import com.jaba.webapp.domain.user.User;
import com.jaba.webapp.repository.specification.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class VideoRepositoryImpl implements VideoRepository {

    @Autowired
    ItemDataContext itemDataContext;

    @Override
    public List<Video> find(Specification<Video> specification) {
        synchronized (itemDataContext.getVideos()) {
            return itemDataContext.getVideos().stream()
                    .filter(video -> specification.matches(video))
                    .filter(video -> video.getAuditInfo().getDeletionDate() == null)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public void addItem(Video item, User user) {
        synchronized (itemDataContext.getVideos()) {
            if (itemDataContext.getVideos().stream().filter(video -> video.getId() == item.getId()).findAny().isPresent())
                throw new IllegalArgumentException(String.format("Video with ID {} already exists", item.getId()));
            item.onCreate(user);
            itemDataContext.getVideos().add(item);
        }
    }

    @Override
    public void removeItem(Video item, User user) {
        synchronized (itemDataContext.getVideos()) {
            for (int i = 0; i < itemDataContext.getVideos().size(); i++) {
                if(itemDataContext.getVideos().get(i).getId() == item.getId()) {
                    if(itemDataContext.getVideos().get(i).getAuditInfo().getDeletionDate() != null)
                        return;
                    itemDataContext.getVideos().get(i).onDelete(user);
                    return;
                }
            }
        }
    }

    @Override
    public void updateItem(Video item, User user) {
        synchronized (itemDataContext.getVideos()) {
            for (int i = 0; i < itemDataContext.getVideos().size(); i++) {
                if(itemDataContext.getVideos().get(i).getId() == item.getId()) {
                    if(itemDataContext.getVideos().get(i).getAuditInfo().getDeletionDate() != null)
                        break;
                    item.onUpdate(user);
                    itemDataContext.getVideos().set(i, item);
                    return;
                }
            }
        }
        throw new IllegalArgumentException(String.format("Video with ID {} doesn't exist", item.getId()));
    }
}
