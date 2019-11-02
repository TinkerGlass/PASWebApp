package com.jaba.webapp.domain.user;

public class AdministratorUser extends User {
    public AdministratorUser(long id, String username, String passwordHash, boolean active) {
        super(id, username, passwordHash, active);
    }

    public AdministratorUser() {
    }
}
