package com.jaba.webapp.repository.specification.item;

import com.jaba.webapp.domain.item.Item;
import com.jaba.webapp.repository.specification.Specification;

public class ItemSpecification {
    public static Specification<Item> byId(Long id) {
        return item -> item.getId().equals(id);
    }

    public static Specification<Item> all() {
        return item -> true;
    }


    public static Specification<Item> allActive() {
        return item -> item.isStatus() == true;
    }

    public static Specification<Item> byTitle(String title) {
        return item -> item.getTitle().equals(title);
    }
}
