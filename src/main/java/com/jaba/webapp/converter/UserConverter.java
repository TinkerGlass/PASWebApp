package com.jaba.webapp.converter;

import com.jaba.webapp.domain.user.AdministratorUser;
import com.jaba.webapp.domain.user.ClientUser;
import com.jaba.webapp.domain.user.ResourceManagerUser;
import com.jaba.webapp.domain.user.User;
import org.springframework.core.convert.converter.Converter;

public class UserConverter implements Converter<User.AccountType, User> {
    @Override
    public User convert(User.AccountType source) {
        switch (source) {
            case CLIENT:
                return new ClientUser();
            case ADMINISTRATOR:
                return new AdministratorUser();
            case RESOURCE_MANAGER:
                return new ResourceManagerUser();
            default:
                throw new IllegalArgumentException("Unknown user type: " + source);
        }
    }
}
