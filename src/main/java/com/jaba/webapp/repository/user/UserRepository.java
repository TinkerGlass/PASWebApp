package com.jaba.webapp.repository.user;

import com.jaba.webapp.domain.user.User;
import com.jaba.webapp.exceptions.ApplicationException;
import com.jaba.webapp.repository.specification.Specification;

import java.util.List;

public interface UserRepository {
    List<User> find(Specification<User> specification);
    void addUser(User user) throws ApplicationException;
    void updateUser(User user);
}
