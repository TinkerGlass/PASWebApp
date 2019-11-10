package com.jaba.webapp.repository.specification;

import com.jaba.webapp.domain.item.Video;

import java.util.Arrays;

public class VideoSpecification {
    public static Specification all() {
        return item -> item instanceof Video;
    }

    public static Specification byTitle(String title) {
        return item -> item instanceof Video && item.getTitle().equals(title);
    }

    public static Specification byGenre(Video.Genre genre) {
        return item -> item instanceof Video && Arrays.stream(((Video)item).getGenres()).filter(g -> g.equals(genre)).count() > 0;
    }

    public static Specification byDirector(String director) {
        return item -> item instanceof Video && ((Video)item).getDirector().equals(director);
    }

    public static Specification byMinutes(int minutes) {
        return item -> item instanceof Video && ((Video)item).getMinutes() == minutes;
    }


}
