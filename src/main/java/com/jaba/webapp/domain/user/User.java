package com.jaba.webapp.domain.user;

import com.jaba.webapp.domain.audit.AuditInfo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


public abstract class User {
    @Id
    private long id;
    private AuditInfo auditInfo;
    private String username;
    private String passwordHash;
    private boolean active;

    public User() {

    }

    public User(long id, String username, String passwordHash, boolean active) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public AuditInfo getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }
}
