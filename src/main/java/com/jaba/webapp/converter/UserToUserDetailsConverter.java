package com.jaba.webapp.converter;

import com.jaba.webapp.domain.user.User;
import com.jaba.webapp.domain.user.UserDetailsImpl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserToUserDetailsConverter implements Converter<User, UserDetails> {
    @Override
    public UserDetails convert(User source) {
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setUsername(source.getUsername());
        userDetails.setPassword(source.getPasswordHash());
        userDetails.setEnabled(source.isActive());
        userDetails.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority(source.getAccountType().getRoleName())));
        return userDetails;
    }
}
