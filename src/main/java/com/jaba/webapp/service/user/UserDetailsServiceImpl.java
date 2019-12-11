package com.jaba.webapp.service.user;

import com.jaba.webapp.converter.UserToUserDetailsConverter;
import com.jaba.webapp.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserManager userManager;
    private UserToUserDetailsConverter userDetailsConverter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userManager.getUserByName(username);
        if(user == null)
            throw new UsernameNotFoundException(String.format( "A user with username {} doesn't exist", username));
        return userDetailsConverter.convert(user);
    }

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    @Autowired
    public void setUserDetailsConverter(UserToUserDetailsConverter userDetailsConverter) {
        this.userDetailsConverter = userDetailsConverter;
    }
}
