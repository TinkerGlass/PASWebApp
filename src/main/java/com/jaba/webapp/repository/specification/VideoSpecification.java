package com.jaba.webapp.repository.specification;

import com.jaba.webapp.domain.item.Video;

public class VideoSpecification {
    public static Specification<Video> all() {
        return item -> true;
    }

    public static Specification<Video> byId(long id) {
        return item -> item.getId() == id;
    }

    public static Specification<Video> byTitle(String title) {
        return item -> item.getTitle().equals(title);
    }

    public static Specification<Video> byGenre(Video.Genre genre) { return item -> item.getGenre().equals(genre); }

    public static Specification<Video> byDirector(String director) { return item -> item.getDirector().equals(director); }

    public static Specification<Video> byMinutes(int minutes) { return item -> item.getMinutes() == minutes; }


}
