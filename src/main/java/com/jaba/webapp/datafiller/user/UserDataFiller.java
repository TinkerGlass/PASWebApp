package com.jaba.webapp.datafiller.user;

import com.jaba.webapp.repository.user.UserRepository;

public interface UserDataFiller {
    void fillUsers(UserRepository repository);
}
