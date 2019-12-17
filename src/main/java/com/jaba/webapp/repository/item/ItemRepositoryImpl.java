package com.jaba.webapp.repository.item;

import com.jaba.webapp.datafiller.item.ItemDataFiller;
import com.jaba.webapp.domain.item.Item;
import com.jaba.webapp.domain.user.User;
import com.jaba.webapp.repository.specification.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private final List<Item> items = new ArrayList<>();

    public ItemRepositoryImpl() { }

    @Autowired(required = false)
    public ItemRepositoryImpl(ItemDataFiller filler) {
        filler.fillItems(this);
    }

    @Override
    public List<Item> find(Specification<Item> specification) {
        synchronized (items) {
            return items.stream()
                    .filter(specification::matches)
                    .collect(Collectors.toList());
        }
    }

    @Secured({User.AccountType.Roles.ADMINISTRATOR_ROLE, User.AccountType.Roles.RESOURCE_MANAGER_ROLE})
    @Override
    public void addItem(Item item) {
        synchronized (items) {
            if (item.getId() != null && items.contains(item))
                throw new IllegalArgumentException(String.format("Item with ID %d already exists", item.getId()));
            item.setId(getNextID());
            items.add(item);
        }
    }

    @Secured({User.AccountType.Roles.ADMINISTRATOR_ROLE, User.AccountType.Roles.RESOURCE_MANAGER_ROLE})
    @Override
    public void removeItem(Long id) {
        synchronized (items) {
            items.removeIf(item -> item.getId().equals(id));
        }
    }
    
    @Override
    public void updateItem(Item item) {
        synchronized (items) {
            int index = items.indexOf(item);
            if (index == -1)
                throw new IllegalArgumentException(String.format("Item with ID %d doesn't exist", item.getId()));
            items.set(index, item);
        }
    }

    private static final AtomicLong _nextID = new AtomicLong(0);
    public static Long getNextID() {
        return _nextID.getAndIncrement();
    }
    private void setNextID(Long value) {
        _nextID.set(value);
    }
}
