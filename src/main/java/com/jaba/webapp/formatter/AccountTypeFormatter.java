package com.jaba.webapp.formatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.jaba.webapp.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.Formatter;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.text.ParseException;
import java.util.Locale;

public class AccountTypeFormatter implements Formatter<User.AccountType> {

    private ResourceBundleMessageSource messageSource;

    @Override
    public User.AccountType parse(String text, Locale locale) throws ParseException {
        if(text.equalsIgnoreCase("client")) {
            return User.AccountType.CLIENT;
        } else if(text.equalsIgnoreCase("resourceManager")) {
            return User.AccountType.RESOURCE_MANAGER;
        } else if(text.equalsIgnoreCase("administrator")) {
            return User.AccountType.ADMINISTRATOR;
        } else {
            throw new IllegalArgumentException("Unknown account type: "+text);
        }
    }

    @Override
    public String print(User.AccountType object, Locale locale) {
        switch(object) {
            case ADMINISTRATOR:
                return messageSource.getMessage("users.type.admin",null, locale);
            case CLIENT:
                return messageSource.getMessage("users.type.client",null, locale);
            case RESOURCE_MANAGER:
                return messageSource.getMessage("users.type.resourceManager",null, locale);
            default:
                return messageSource.getMessage("users.type.unknown",null, locale);
        }
    }

    @Autowired
    public void setMessageSource(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
