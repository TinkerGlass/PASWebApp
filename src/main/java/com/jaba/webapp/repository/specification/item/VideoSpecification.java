package com.jaba.webapp.repository.specification.item;

import com.jaba.webapp.domain.item.Item;
import com.jaba.webapp.domain.item.Video;
import com.jaba.webapp.repository.specification.Specification;

import java.util.Arrays;

public class VideoSpecification {
    public static Specification<Item> all() {
        return item -> item instanceof Video;
    }

    public static Specification<Item> byTitle(String title) {
        return item -> item instanceof Video && item.getTitle().equals(title);
    }

    public static Specification<Item> byGenre(Video.Genre genre) {
        return item -> item instanceof Video && Arrays.asList(((Video) item).getGenre()).contains(genre);
    }

    public static Specification<Item> byDirector(String director) {
        return item -> item instanceof Video && ((Video)item).getDirector().equals(director);
    }

    public static Specification<Item> byMinutes(int minutes) {
        return item -> item instanceof Video && ((Video)item).getMinutes() == minutes;
    }


}
