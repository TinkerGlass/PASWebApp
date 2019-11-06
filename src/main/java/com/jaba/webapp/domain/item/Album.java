package com.jaba.webapp.domain.item;

import com.jaba.webapp.domain.audit.AuditInfo;
import java.util.Date;


public class Album extends Item {
    private String title;
    private String author;
    private Genre genre;
    private Date releaseDate;
    private int tracks;

    public Album() {

    }

    public Album(long id, AuditInfo auditInfo, String title, String author, Genre genre, Date releaseDate, int tracks) {
        this.setId(id);
        this.setAuditInfo(auditInfo);
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.tracks = tracks;
    }

    public String toString(){
        return this.getId() + " " + this.getAuditInfo() + " " + this.title + " " + this.author + " " + this.genre;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getTracks() {
        return tracks;
    }

    public void setTracks(int tracks) {
        this.tracks = tracks;
    }

    public enum Genre {
        POP,
        ROCK,
        HIPHOP,
        CLASSICAL,
        EDM
    }
}
