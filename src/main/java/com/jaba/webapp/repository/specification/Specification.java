package com.jaba.webapp.repository.specification;

import com.jaba.webapp.domain.item.Item;

public interface Specification {
    boolean matches(Item item);
}
