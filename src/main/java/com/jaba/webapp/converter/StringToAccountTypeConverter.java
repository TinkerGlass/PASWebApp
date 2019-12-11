package com.jaba.webapp.converter;

import com.jaba.webapp.domain.user.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToAccountTypeConverter implements Converter<String, User.AccountType> {

    @Override
    public User.AccountType convert(String source) {
        for(User.AccountType type : User.AccountType.values()) {
            if (type.toString().equals(source))
                return type;
        }
        throw new IllegalArgumentException("Unknown account type: "+source);
    }
}
