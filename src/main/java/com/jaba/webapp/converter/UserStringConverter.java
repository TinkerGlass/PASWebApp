package com.jaba.webapp.converter;

import com.jaba.webapp.domain.user.User;
import org.springframework.core.convert.converter.Converter;

public class UserStringConverter implements Converter<String, User> {
    @Override
    public User convert(String source) {
        if(source.equalsIgnoreCase(User.AccountType.RESOURCE_MANAGER.toString())) {
            return new User(User.AccountType.RESOURCE_MANAGER);
        } else if(source.equalsIgnoreCase(User.AccountType.ADMINISTRATOR.toString())) {
            return new User(User.AccountType.CLIENT);
        } else if(source.equalsIgnoreCase(User.AccountType.CLIENT.toString())){
            return new User(User.AccountType.CLIENT);
        } else {
            throw new IllegalArgumentException("Unknown user type: " + source);
        }
    }
}
