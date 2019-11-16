package com.jaba.webapp.repository.item;

import com.jaba.webapp.domain.item.Item;
import com.jaba.webapp.repository.specification.Specification;

import java.util.List;

public interface ItemRepository {
    List<Item> find(Specification<Item> specification);
    void addItem(Item item);
    void removeItem(Long id);
    void updateItem(Item item);
}
