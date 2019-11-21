package com.jaba.webapp.converter;

import com.jaba.webapp.domain.item.FanSticker;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class FanStickerFormatter implements Formatter<FanSticker> {
    @Override
    public FanSticker parse(String text, Locale locale) throws ParseException {
        return null;
    }

    @Override
    public String print(FanSticker object, Locale locale) {
        return object.getStickerName()+" +"+object.getStickerPoints();
    }
}
