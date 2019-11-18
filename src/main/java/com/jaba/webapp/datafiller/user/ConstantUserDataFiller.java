package com.jaba.webapp.datafiller.user;

import com.jaba.webapp.domain.user.ClientUser;
import com.jaba.webapp.domain.user.User;
import com.jaba.webapp.repository.user.UserRepository;

public class ConstantUserDataFiller  implements UserDataFiller{

    final User[] users = {
            new ClientUser("BorryOllo", "flash", true)
    };

    @Override
    public void fillUsers(UserRepository repository) {
        for(User user : users) {
            repository.addUser(user);
        }
    }
}
