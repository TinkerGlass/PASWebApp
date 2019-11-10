package com.jaba.webapp.repository.specification;

import com.jaba.webapp.domain.item.Album;

public class AlbumSpecification {
    public static Specification all() {
        return item -> item instanceof Album;
    }

    public static Specification byTitle(String title) {
        return item -> item instanceof Album && item.getTitle().equals(title);
    }

    public static Specification byAuthor(String author) {
        return item -> item instanceof Album && ((Album)item).getAuthor().equals(author);
    }

    public static Specification byGenre(Album.Genre genre) {
        return item -> item instanceof Album && ((Album)item).getGenre().equals(genre);
    }

    public static Specification byTracksNumber(int tracks) {
        return item -> item instanceof Album && ((Album)item).getTracks() == tracks;
    }

}
