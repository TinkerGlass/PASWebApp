package com.jaba.webapp.repository;

import com.jaba.webapp.datacontext.datafiller.ItemDataFiller;
import com.jaba.webapp.domain.item.Item;
import com.jaba.webapp.repository.specification.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private List<Item> items = Collections.synchronizedList(new ArrayList<Item>());

    public ItemRepositoryImpl() {

    }

    @Autowired(required = false)
    public ItemRepositoryImpl(ItemDataFiller filler) {
        filler.fillItems(this);
    }

    @Override
    public List<Item> find(Specification specification) {
        synchronized (items) {
            return items.stream()
                    .filter(item -> specification.matches(item))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public void addItem(Item item) {
        synchronized (items) {
            if (item.getId() != null && items.contains(item))
                throw new IllegalArgumentException(String.format("Item with ID {} already exists", item.getId()));
            item.setId(getNextID());
            items.add(item);
        }
    }

    @Override
    public void removeItem(Item item) {
        items.remove(item);
    }

    @Override
    public void updateItem(Item item) {
        int index = items.indexOf(item);
        if(index == -1)
            throw new IllegalArgumentException(String.format("Item with ID {} doesn't exist", item.getId()));
        items.set(index, item);
    }

    private static AtomicLong _nextID = new AtomicLong(0);
    public static Long getNextID() {
        return _nextID.getAndIncrement();
    }
    private void setNextID(Long value) {
        _nextID.set(value);
    }
}
