package com.jaba.webapp.repository.specification;

public class ItemSpecification {
    public static Specification byId(Long id) {
        return item -> item.getId().equals(id);
    }

    public static Specification all() {
        return item -> true;
    }

    public static Specification byTitle(String title) {
        return item -> item.getTitle().equals(title);
    }
}
