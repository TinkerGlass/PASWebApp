package com.jaba.webapp.repository;

import com.jaba.webapp.repository.specification.Specification;

import java.util.List;

public interface CRUDRepository<T> {
    List<T> find(Specification<T> specification);
    void addItem(T item);
    void removeItem(T item);
    void updateItem(T item);
}
