package com.jaba.webapp.domain.user;

public class AdministratorUser extends User {
    public AdministratorUser(String username, String passwordHash, boolean active) {
        super(username, passwordHash, active);
        accountType = AccountType.ADMINISTRATOR;
    }

    public AdministratorUser() {
        accountType = AccountType.ADMINISTRATOR;
    }
}
