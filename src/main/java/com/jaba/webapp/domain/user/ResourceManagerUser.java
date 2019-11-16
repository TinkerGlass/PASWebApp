package com.jaba.webapp.domain.user;

public class ResourceManagerUser extends User {
    public ResourceManagerUser() {
    }

    public ResourceManagerUser(String username, String passwordHash, boolean active) {
        super(username, passwordHash, active);
    }
}
