package com.jaba.webapp.domain.item;

import com.jaba.webapp.domain.audit.AuditInfo;
import com.jaba.webapp.domain.user.User;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;


public abstract class Item {
    @Id
    @GeneratedValue
    private long id;
    private AuditInfo auditInfo;

    public Item() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AuditInfo getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }
}
