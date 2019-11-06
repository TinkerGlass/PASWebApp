package com.jaba.webapp.repository.specification;

import com.jaba.webapp.domain.item.Album;

public class AlbumSpecification {
    public static Specification<Album> all() {
        return item -> true;
    }

    public static Specification<Album> byId(long id) {
        return item -> item.getId() == id;
    }

    public static Specification<Album> byTitle(String title) {
        return item -> item.getTitle().equals(title);
    }

    public static Specification<Album> byAuthor(String author) {
        return item -> item.getAuthor().equals(author);
    }

    public static Specification<Album> byGenre(Album.Genre genre) {
        return item -> item.getGenre().equals(genre);
    }

    public static Specification<Album> byTracksNumber(int tracks) {
        return item -> item.getTracks() == tracks;
    }

}
