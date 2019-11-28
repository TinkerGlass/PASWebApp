package com.jaba.webapp.service.item;

import com.jaba.webapp.domain.item.Item;
import com.jaba.webapp.exceptions.ApplicationException;

import java.util.List;

public interface ItemManager {
    List<Item> getAllItems();
    List<Item> getAllActiveItems();
    Item getItemById(Long id);
    void deleteItem(Long id);
    void updateItem(Item item) throws ApplicationException;
    void addItem(Item item);

}
