package com.jaba.webapp.domain.user;

public class ClientUser extends User {
    public ClientUser() {
        accountType = AccountType.CLIENT;
        setActive(true);
    }

    public ClientUser(String username, String passwordHash, boolean active) {
        super(username, passwordHash, active);
        accountType = AccountType.CLIENT;
    }
}
