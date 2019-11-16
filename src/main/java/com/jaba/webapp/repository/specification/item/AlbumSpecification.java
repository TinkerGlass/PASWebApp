package com.jaba.webapp.repository.specification.item;

import com.jaba.webapp.domain.item.Album;
import com.jaba.webapp.domain.item.Item;
import com.jaba.webapp.repository.specification.Specification;

public class AlbumSpecification {
    public static Specification<Item> all() {
        return item -> item instanceof Album;
    }

    public static Specification<Item> byTitle(String title) {
        return item -> item instanceof Album && item.getTitle().equals(title);
    }

    public static Specification<Item> byAuthor(String author) {
        return item -> item instanceof Album && ((Album)item).getAuthor().equals(author);
    }

    public static Specification<Item> byGenre(Album.Genre genre) {
        return item -> item instanceof Album && ((Album)item).getGenre().equals(genre);
    }

    public static Specification<Item> byTracksNumber(int tracks) {
        return item -> item instanceof Album && ((Album)item).getTracks() == tracks;
    }

}
