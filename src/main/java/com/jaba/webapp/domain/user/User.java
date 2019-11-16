package com.jaba.webapp.domain.user;

public abstract class User {
    private Long id;
    private String username;
    private String passwordHash;
    private boolean active;

    public User() {

    }

    public User(String username, String passwordHash, boolean active) {
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

}
