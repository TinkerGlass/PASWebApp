package com.jaba.webapp.repository;

import com.jaba.webapp.domain.item.Item;
import com.jaba.webapp.domain.user.User;
import com.jaba.webapp.repository.specification.Specification;

import java.util.List;

public interface ItemRepository {
    List<Item> find(Specification specification);
    void addItem(Item item, User user);
    void removeItem(Item item, User user);
    void updateItem(Item item, User user);
}
