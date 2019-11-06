package com.jaba.webapp.repository;

import com.jaba.webapp.domain.user.User;
import com.jaba.webapp.repository.specification.Specification;

import java.util.List;

public interface CRUDRepository<T> {
    List<T> find(Specification<T> specification);
    void addItem(T item, User user);
    void removeItem(T item, User user);
    void updateItem(T item, User user);
}
