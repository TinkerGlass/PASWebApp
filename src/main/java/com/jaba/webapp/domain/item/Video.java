package com.jaba.webapp.domain.item;

import com.jaba.webapp.domain.audit.AuditInfo;

import java.util.Date;
public class Video extends Item {
    private String title;
    private Date releaseDate;
    private Genre genre;
    private String director;
    private int minutes;

    public Video() {

    }

    public Video(long id, AuditInfo auditInfo, String title, Date releaseDate, Genre genre, String director, int minutes) {
        this.setId(id);
        this.setAuditInfo(auditInfo);
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
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

    public enum Genre {
        COMEDY,
        DRAMA,
        SCIFI,
        HORROR,
        ANIME
    }
}
