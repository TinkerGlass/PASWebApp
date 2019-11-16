package com.jaba.webapp.service;

import com.jaba.webapp.domain.item.Item;

import java.util.List;

public interface ItemManager {
    List<Item> getAllItems();
    Item getItemById(Long id);
    void deleteItem(Long id);
    void updateItem(Long id);

}
