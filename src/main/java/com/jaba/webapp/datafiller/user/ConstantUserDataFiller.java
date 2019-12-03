package com.jaba.webapp.datafiller.user;

import com.jaba.webapp.domain.user.User;
import com.jaba.webapp.repository.user.UserRepository;

public class ConstantUserDataFiller  implements UserDataFiller{

    final User[] users = {
            new User("BorryOllo", "flash", true, User.AccountType.CLIENT),
            new User("Patty221", "aksudh34982r9j", true, User.AccountType.CLIENT),
            new User("Ghana", "fchj394poksd", true, User.AccountType.CLIENT),
            new User("Superuser", "kjsndf8943qwe", true, User.AccountType.ADMINISTRATOR),
            new User("Jessie", "oiu234olnksfd", true, User.AccountType.RESOURCE_MANAGER),
    };

    @Override
    public void fillUsers(UserRepository repository) {
        for(User user : users) {
            try {
                repository.addUser(user);
            } catch(Exception e) {}
        }
    }
}
