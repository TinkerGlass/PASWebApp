package com.jaba.webapp.domain.audit;

import com.jaba.webapp.domain.user.RootUser;
import com.jaba.webapp.domain.user.User;;
import java.util.Date;

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

    public AuditInfo(long id, Date creationDate)
    {
        this.id = id;
        this.creationDate = creationDate;
        this.creationUser = RootUser.getRootUser();
    }

    public AuditInfo(long id, Date creationDate, User creationUser)
    {
        this.id = id;
        this.creationDate = creationDate;
        this.creationUser = creationUser;
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
}
