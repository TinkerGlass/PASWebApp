package com.jaba.webapp.formatter;

import com.jaba.webapp.domain.item.Album;
import com.jaba.webapp.domain.item.Video;
import com.jaba.webapp.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class AlbumGenreFormatter implements Formatter<Album.Genre> {

    @Override
    public Album.Genre parse(String text, Locale locale) throws ParseException {
        String value = text.replaceFirst("album.genres.", "");
        for(Album.Genre genre : Album.Genre.values()) {
            if(value.equalsIgnoreCase(genre.toString()))
                return genre;
        }
        throw new IllegalArgumentException("Unknown movie genre: "+text);
    }

    @Override
    public String print(Album.Genre object, Locale locale) {
        return "album.genres."+object.toString().toLowerCase(Locale.ROOT);
    }

}
