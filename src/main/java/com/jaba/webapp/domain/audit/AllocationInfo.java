package com.jaba.webapp.domain.audit;

import com.jaba.webapp.domain.item.Item;
import com.jaba.webapp.domain.user.User;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class AllocationInfo {
    private Long id;
    @NotNull
    private Item item;
    @NotNull
    private User user;
    private Date startTime;
    private Date endTime;

    public AllocationInfo() {

    }

    public AllocationInfo(Item item, User user, Date startTime) {
        this.item = item;
        this.user = user;
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
