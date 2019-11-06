package com.jaba.webapp.domain.item;

import java.util.Arrays;
import java.util.Date;

public class Video extends Item {
    private String title;
    private Date releaseDate;
    private Genre[] genres;
    private String director;
    private int minutes;

    public Video() {

    }

    public Video(String title, String director, Genre[] genres, Date releaseDate, int minutes) {
        this.setId(getNextID());
        this.title = title;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.director = director;
        this.minutes = minutes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
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
        return this.getId() + " " + this.getAuditInfo() + " " + this.title + " " + this.director + " " + Arrays.toString(genres);
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
