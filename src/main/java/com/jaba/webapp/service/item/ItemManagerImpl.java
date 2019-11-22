package com.jaba.webapp.service.item;

import com.jaba.webapp.domain.item.Item;
import com.jaba.webapp.repository.item.ItemRepository;
import com.jaba.webapp.repository.specification.item.ItemSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemManagerImpl implements ItemManager {

    private ItemRepository itemRepository;

    @Override
    public List<Item> getAllItems() {
        return itemRepository.find(ItemSpecification.all());
    }


    public List<Item> getAllActiveItems() {
        return itemRepository.find(ItemSpecification.allActive());
    }

    @Override
    public Item getItemById(Long id) {
        List<Item> items = itemRepository.find(ItemSpecification.byId(id));
        return items.size() == 0 ? null : items.get(0);
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.removeItem(id);
    }

    @Override
    public void updateItem(Item item) {
        itemRepository.updateItem(item);
    }

    @Override
    public void addItem(Item item) {
        itemRepository.addItem(item);
    }

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
}
