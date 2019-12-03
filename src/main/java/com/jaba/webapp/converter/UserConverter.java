package com.jaba.webapp.converter;

import com.jaba.webapp.domain.user.User;
import org.springframework.core.convert.converter.Converter;

public class UserConverter implements Converter<User.AccountType, User> {
    @Override
    public User convert(User.AccountType source) {
        switch (source) {
            case CLIENT:
                return new User(User.AccountType.CLIENT);
            case ADMINISTRATOR:
                return new User(User.AccountType.ADMINISTRATOR);
            case RESOURCE_MANAGER:
                return new User(User.AccountType.RESOURCE_MANAGER);
            default:
                throw new IllegalArgumentException("Unknown user type: " + source);
        }
    }
}
