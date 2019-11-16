package com.jaba.webapp.repository.user;

import com.jaba.webapp.datafiller.user.UserDataFiller;
import com.jaba.webapp.domain.user.User;
import com.jaba.webapp.repository.specification.Specification;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements UserRepository {

    private final List<User> users = new ArrayList<>();

    public UserRepositoryImpl() {}

    @Autowired(required = false)
    public UserRepositoryImpl(UserDataFiller filler) {
        filler.fillUsers(this);
    }

    @Override
    public List<User> find(Specification<User> specification) {
        synchronized (users) {
            return users.stream()
                    .filter(specification::matches)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public void addUser(User user) {
        synchronized (users) {
            if (user.getId() != null && users.contains(user))
                throw new IllegalArgumentException(String.format("User with ID %d already exists", user.getId()));
            user.setId(getNextID());
            users.add(user);
        }
    }

    @Override
    public void updateUser(User user) {
        synchronized (users) {
            int index = users.indexOf(user);
            if (index == -1)
                throw new IllegalArgumentException(String.format("User with ID %d doesn't exist", user.getId()));
            users.set(index, user);
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
