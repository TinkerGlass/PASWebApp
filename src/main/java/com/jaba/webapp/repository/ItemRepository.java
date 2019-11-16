package com.jaba.webapp.repository;

import com.jaba.webapp.domain.item.Item;
import com.jaba.webapp.repository.specification.Specification;

import java.util.List;

public interface ItemRepository {
    List<Item> find(Specification specification);
    void addItem(Item item);
    void removeItem(Item item);
    void updateItem(Item item);
}
