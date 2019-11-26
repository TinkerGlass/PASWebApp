package com.jaba.webapp.domain.item;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class Video extends Item {
    @NotNull(message="general.validation.empty")
    private Genre genre;
    @NotBlank(message="general.validation.empty")
    private String director;
    @Min(value = 1, message = "general.validation.minValue")
    private int minutes;

    public Video() {

    }

    public Video(String title,
                 String director,
                 Genre genre,
                 Date releaseDate,
                 int minutes,
                 BigDecimal price,
                 FanSticker sticker,
                 boolean status) {
        super(title, price, releaseDate, sticker, status);
        this.genre = genre;
        this.director = director;
        this.minutes = minutes;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genres) {
        this.genre = genres;
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
        return this.getId() + " " + this.getTitle() + " " + this.director + " " + genre.toString() + " " + this.getPrice();
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
