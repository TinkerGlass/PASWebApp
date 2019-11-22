package com.jaba.webapp.domain.user;

public class ResourceManagerUser extends User {
    public ResourceManagerUser() {
        accountType = AccountType.RESOURCE_MANAGER;
        setActive(true);
    }

    public ResourceManagerUser(String username, String passwordHash, boolean active) {
        super(username, passwordHash, active);
        accountType = AccountType.RESOURCE_MANAGER;
    }
}
