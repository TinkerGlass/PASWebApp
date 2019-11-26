package com.jaba.webapp.formatter;

import com.jaba.webapp.domain.item.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class VideoGenreFormatter implements Formatter<Video .Genre> {
    private ResourceBundleMessageSource messageSource;

    @Override
    public Video.Genre parse(String text, Locale locale) throws ParseException {
        return null;
    }

    @Override
    public String print(Video.Genre object, Locale locale) {
        switch(object) {
            case ANIME:
                return messageSource.getMessage("video.genres.anime", null,locale);
            case SCIFI:
                return messageSource.getMessage("video.genres.scifi", null,locale);
            case COMEDY:
                return messageSource.getMessage("video.genres.comedy", null,locale);
            case FANTASY:
                return messageSource.getMessage("video.genres.fantasy", null,locale);
            case MUSICAL:
                return messageSource.getMessage("video.genres.musical", null,locale);
            case THRILLER:
                return messageSource.getMessage("video.genres.thriller", null,locale);
            case DRAMA:
                return messageSource.getMessage("video.genres.drama", null,locale);
            case ACTION:
                return messageSource.getMessage("video.genres.action", null,locale);
            case HORROR:
                return messageSource.getMessage("video.genres.horror", null,locale);
        }

        throw new IllegalArgumentException("Unknown movie genre: "+object);
    }

    @Autowired
    public void setMessageSource(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
