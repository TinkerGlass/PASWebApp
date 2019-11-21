package com.jaba.webapp.service.item;

import com.jaba.webapp.domain.item.Item;

import java.util.List;

public interface ItemManager {
    List<Item> getAllItems();
    Item getItemById(Long id);
    void deleteItem(Long id);
    void updateItem(Item item);
    void addItem(Item item);

}
