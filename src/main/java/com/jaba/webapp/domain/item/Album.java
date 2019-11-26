package com.jaba.webapp.domain.item;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;


public class Album extends Item {
    @NotBlank(message = "general.validation.empty")
    private String author;
    @NotNull(message = "general.validation.empty")
    private Genre genre;
    @Min(value = 1, message = "general.validation.minValue")
    private int tracks;

    public Album() {

    }

    public Album(String title,
                 String author,
                 Genre genre,
                 Date releaseDate,
                 int tracks,
                 BigDecimal price,
                 FanSticker sticker,
                 boolean status) {
        super(title, price, releaseDate, sticker, status);
        this.author = author;
        this.genre = genre;
        this.tracks = tracks;
    }

    public String toString(){
        return this.getId() + " " + this.getTitle() + " " + this.author + " " + this.genre + " " + this.getPrice();
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
