package com.jaba.webapp.formatter;

import org.springframework.format.Formatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter implements Formatter<Date> {
    
    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return df.parse(text);
    }

    @Override
    public String print(Date object, Locale locale) {
        if(locale == Locale.US) {
            DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
            return df.format(object);
        } else if(locale.getCountry().equals("PL")) {
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            return df.format(object);
        } else {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            return df.format(object);
        }

    }
}
