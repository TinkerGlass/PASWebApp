package com.jaba.webapp.domain.item;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class Video extends Item {
    private Genre[] genres;
    private String director;
    private int minutes;

    public Video() {

    }

    public Video(String title, String director, Genre[] genres, Date releaseDate, int minutes, BigDecimal price) {
        super(title, price, releaseDate);
        this.genres = genres;
        this.director = director;
        this.minutes = minutes;
    }

    public Genre[] getGenres() {
        return genres;
    }

    public void setGenres(Genre[] genres) {
        this.genres = genres;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String toString(){
        return this.getId() + " " + this.getTitle() + " " + this.director + " " + Arrays.toString(genres) + " " + this.getPrice();
    }

    public enum Genre {
        COMEDY,
        DRAMA,
        SCIFI,
        HORROR,
        ANIME,
        FANTASY,
        MUSICAL,
        THRILLER,
        ACTION
    }
}
