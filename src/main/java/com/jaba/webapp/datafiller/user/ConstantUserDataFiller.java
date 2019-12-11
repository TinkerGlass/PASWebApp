package com.jaba.webapp.datafiller.user;

import com.jaba.webapp.domain.user.User;
import com.jaba.webapp.repository.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class ConstantUserDataFiller  implements UserDataFiller{

    final User[] users = {
            new User("BorryOllo", "flash", true, User.AccountType.CLIENT),
            new User("Patty221", "aksudh34982r9j", true, User.AccountType.CLIENT),
            new User("Ghana", "fchj394poksd", true, User.AccountType.CLIENT),
            new User("Superuser", "admin", true, User.AccountType.ADMINISTRATOR),
            new User("Jessie", "mgr", true, User.AccountType.RESOURCE_MANAGER),
    };

    @Override
    public void fillUsers(UserRepository repository) {
        for(User user : users) {
            try {
                user.setPasswordHash(BCrypt.hashpw(user.getPasswordHash(), BCrypt.gensalt()));
                repository.addUser(user);
            } catch(Exception e) {}
        }
    }
}
