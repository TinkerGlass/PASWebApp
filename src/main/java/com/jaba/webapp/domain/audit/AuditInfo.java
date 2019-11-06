package com.jaba.webapp.domain.audit;

import com.jaba.webapp.domain.user.User;;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class AuditInfo {
    private long id;
    private Date creationDate;
    private User creationUser;
    private Date modificationDate;
    private User modificationUser;
    private Date deletionDate;
    private User deletionUser;

    public AuditInfo() {

    }

    public AuditInfo(User creationUser) {
        this.id = getNextID();
        this.creationDate = Date.from(Instant.now());
        this.creationUser = creationUser;
    }

    public void updateExecuted(User modificationUser) {
        this.modificationDate = Date.from(Instant.now());
        this.modificationUser = modificationUser;
    }

    public void deleteExecuted(User deletionUser) {
        this.deletionDate = Date.from(Instant.now());
        this.deletionUser = deletionUser;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(User creationUser) {
        this.creationUser = creationUser;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public User getModificationUser() {
        return modificationUser;
    }

    public void setModificationUser(User modificationUser) {
        this.modificationUser = modificationUser;
    }

    public Date getDeletionDate() {
        return deletionDate;
    }

    public void setDeletionDate(Date deletionDate) {
        this.deletionDate = deletionDate;
    }

    public User getDeletionUser() {
        return deletionUser;
    }

    public void setDeletionUser(User deletionUser) {
        this.deletionUser = deletionUser;
    }


    private static AtomicLong _nextID = new AtomicLong(0);

    public static long getNextID() {
        return _nextID.getAndIncrement();
    }

    private void setNextID(long value) {
        _nextID.set(value);
    }
}
