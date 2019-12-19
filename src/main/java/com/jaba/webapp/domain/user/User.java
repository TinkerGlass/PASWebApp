package com.jaba.webapp.domain.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jaba.webapp.controller.jsonviews.JSONViews;
import com.jaba.webapp.formatter.AccountTypeFormatter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class User {
    private Long id;
    @NotBlank(message = "{users.validation.empty}")
    @Length(min = 5, message = "{users.validation.length}")
    private String username;
    @NotBlank(message = "{general.validation.empty}")
    @Length(min = 8, message = "{general.validation.minLength}")
    @JsonView(JSONViews.UserDetailsView.class)
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
        CLIENT(Roles.CLIENT), RESOURCE_MANAGER(Roles.RESOURCE_MANAGER), ADMINISTRATOR(Roles.ADMINISTRATOR);

        private String name;
        public static final String role_prefix = "ROLE_";

        AccountType(String name) {
            this.name = name;
        }

        public String getRoleName() {
            return role_prefix + name;
        }

        public static class Roles {
            public static final String CLIENT = "CLIENT";
            public static final String RESOURCE_MANAGER = "RESOURCE_MANAGER";
            public static final String ADMINISTRATOR = "ADMINISTRATOR";

            public static final String CLIENT_ROLE = role_prefix+CLIENT;
            public static final String RESOURCE_MANAGER_ROLE = role_prefix+RESOURCE_MANAGER;
            public static final String ADMINISTRATOR_ROLE = role_prefix+ADMINISTRATOR;
        }
    }
}
