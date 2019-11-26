package com.jaba.webapp.formatter;

import com.jaba.webapp.domain.item.Album;
import com.jaba.webapp.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class AlbumGenreFormatter implements Formatter<Album.Genre> {

    private ResourceBundleMessageSource messageSource;

    @Override
    public Album.Genre parse(String text, Locale locale) throws ParseException {
        String a = Album.Genre.POP.toString();
        if(text.equalsIgnoreCase("pop")) {
            return Album.Genre.POP;
        } else if(text.equalsIgnoreCase("rock")) {
            return Album.Genre.ROCK;
        } else if(text.equalsIgnoreCase("hiphop")) {
            return Album.Genre.HIPHOP;
        } else if(text.equalsIgnoreCase("classical")) {
            return Album.Genre.CLASSICAL;
        } else if(text.equalsIgnoreCase("edm")) {
            return Album.Genre.EDM;
        }
        throw new IllegalArgumentException("Unknown album genre: " + text);
    }

    @Override
    public String print(Album.Genre object, Locale locale) {
        switch(object) {
            case POP:
                return messageSource.getMessage("album.genres.pop",null, locale);
            case ROCK:
                return messageSource.getMessage("album.genres.rock",null, locale);
            case HIPHOP:
                return messageSource.getMessage("album.genres.hiphop",null, locale);
            case CLASSICAL:
                return messageSource.getMessage("album.genres.classical",null, locale);
            case EDM:
                return messageSource.getMessage("album.genres.edm",null, locale);
        }
        throw new IllegalArgumentException("Unknown album genre: " + object);
    }

    @Autowired
    public void setMessageSource(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
