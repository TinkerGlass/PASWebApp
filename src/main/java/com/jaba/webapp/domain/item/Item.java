package com.jaba.webapp.domain.item;

import com.jaba.webapp.domain.audit.AuditInfo;
import com.jaba.webapp.domain.user.User;

import java.util.concurrent.atomic.AtomicLong;


public abstract class Item {
    private long id;
    private AuditInfo auditInfo;

    public Item() {

    }

    public void onCreate(User user) {
        auditInfo = new AuditInfo(user);
    }

    public void onUpdate(User user) {
        auditInfo.updateExecuted(user);
    }

    public void onDelete(User user) {
        auditInfo.deleteExecuted(user);
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

    private static AtomicLong _nextID = new AtomicLong(0);

    public static long getNextID() {
        return _nextID.getAndIncrement();
    }

    private void setNextID(long value) {
        _nextID.set(value);
    }
}
