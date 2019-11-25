package com.jaba.webapp.converter;

import com.jaba.webapp.domain.item.Album;
import com.jaba.webapp.domain.item.Item;
import com.jaba.webapp.domain.item.Video;
import org.springframework.core.convert.converter.Converter;

public class ItemStringConverter  implements Converter<String, Item> {
    @Override
    public Item convert(String source) {
        if(source.equalsIgnoreCase("video")) {
            return new Video();
        } else if(source.equalsIgnoreCase("album")) {
            return new Album();
        } else {
            throw new IllegalArgumentException("Unknown user type: " + source);
        }
    }
}
