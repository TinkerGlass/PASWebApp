package com.jaba.webapp.datafiller.user;

import com.jaba.webapp.domain.user.AdministratorUser;
import com.jaba.webapp.domain.user.ClientUser;
import com.jaba.webapp.domain.user.ResourceManagerUser;
import com.jaba.webapp.domain.user.User;
import com.jaba.webapp.repository.user.UserRepository;

public class ConstantUserDataFiller  implements UserDataFiller{

    final User[] users = {
            new ClientUser("BorryOllo", "flash", true),
            new ClientUser("Patty221", "aksudh34982r9j", true),
            new ClientUser("Ghana", "fchj394poksd", true),
            new AdministratorUser("Superuser", "kjsndf8943qwe", true),
            new ResourceManagerUser("Jessie", "oiu234olnksfd", true),
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
