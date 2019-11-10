package com.jaba.webapp.domain.item;

import com.jaba.webapp.domain.audit.AuditInfo;
import com.jaba.webapp.domain.user.User;

import java.math.BigDecimal;
import java.util.Date;


public abstract class Item {
    private Long id;
    private BigDecimal price;
    private String title;
    private Date releaseDate;
    private AuditInfo auditInfo;

    public Item(String title, BigDecimal price, Date releaseDate) {
        this.title = title;
        this.price = price;
        this.releaseDate = releaseDate;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuditInfo getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Item) && getId() != null && ((Item)o).getId().equals(getId());
    }
}
