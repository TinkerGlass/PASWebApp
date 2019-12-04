package com.jaba.webapp.domain.user;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class User {
    private Long id;
    @NotBlank(message = "{users.validation.empty}")
    @Length(min = 5, message = "{users.validation.length}")
    private String username;
    private String passwordHash;
    @NotNull
    protected AccountType accountType;
    private boolean active;

    public User() { }

    public User(String username, String passwordHash, boolean active, AccountType accountType) {
        this();
        this.username = username;
        this.passwordHash = passwordHash;
        this.active = active;
        this.accountType = accountType;
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

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public enum AccountType {
        CLIENT, RESOURCE_MANAGER, ADMINISTRATOR
    }
}
