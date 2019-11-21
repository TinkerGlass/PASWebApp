package com.jaba.webapp.domain.user;

public abstract class User {
    private Long id;
    private String username;
    private String passwordHash;
    protected AccountType accountType;
    private boolean active;

    public User() {
        accountType = AccountType.UNKNOWN;
    }

    public User(String username, String passwordHash, boolean active) {
        this();
        this.username = username;
        this.passwordHash = passwordHash;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public enum AccountType {
        UNKNOWN, CLIENT, RESOURCE_MANAGER, ADMINISTRATOR
    }
}
