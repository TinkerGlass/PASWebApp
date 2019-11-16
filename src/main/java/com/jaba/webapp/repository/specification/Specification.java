package com.jaba.webapp.repository.specification;

public interface Specification<T> {
    boolean matches(T object);
}
