package com.jaba.webapp.formatter;

import com.jaba.webapp.domain.item.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class VideoGenreFormatter implements Formatter<Video .Genre> {
    @Override
    public Video.Genre parse(String text, Locale locale) throws ParseException {
        String value = text.replaceFirst("video.genres.", "");
        for(Video.Genre genre : Video.Genre.values()) {
            if(value.equalsIgnoreCase(genre.toString()))
                return genre;
        }
        throw new IllegalArgumentException("Unknown movie genre: "+text);
    }

    @Override
    public String print(Video.Genre object, Locale locale) {
        return "video.genres."+object.toString().toLowerCase(Locale.ROOT);
    }
}
