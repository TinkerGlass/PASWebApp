package com.jaba.webapp.domain.item;

import com.jaba.webapp.domain.audit.AuditInfo;
import com.jaba.webapp.domain.user.User;

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

    public enum Genre {
        POP,
        ROCK,
        HIPHOP,
        CLASSICAL,
        EDM
    }
}
