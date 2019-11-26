package com.jaba.webapp.converter;

import com.jaba.webapp.domain.item.FanSticker;
import org.springframework.core.convert.converter.Converter;

public class FanStickerConverter implements Converter<String, FanSticker> {
    @Override
    public FanSticker convert(String source) {
        if(source.equalsIgnoreCase("sticker")) {
            return new FanSticker();
        } else {
            throw new IllegalArgumentException("Unknown user type: " + source);
        }
    }
}
