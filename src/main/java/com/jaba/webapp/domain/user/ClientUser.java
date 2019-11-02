package com.jaba.webapp.domain.user;

public class ClientUser extends User {
    public ClientUser() {
    }

    public ClientUser(long id, String username, String passwordHash, boolean active) {
        super(id, username, passwordHash, active);
    }
}
