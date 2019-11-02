package com.jaba.webapp.domain.user;

public class ResourceManagerUser extends User {
    public ResourceManagerUser() {
    }

    public ResourceManagerUser(long id, String username, String passwordHash, boolean active) {
        super(id, username, passwordHash, active);
    }
}
