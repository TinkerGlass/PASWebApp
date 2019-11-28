package com.jaba.webapp.repository.user;

import com.jaba.webapp.datafiller.user.UserDataFiller;
import com.jaba.webapp.domain.user.User;
import com.jaba.webapp.exceptions.ApplicationException;
import com.jaba.webapp.repository.specification.Specification;
import com.jaba.webapp.repository.specification.user.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
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
    public void addUser(User user) throws ApplicationException {
        synchronized (users) {
            if (user.getId() != null && users.contains(user))
                throw new IllegalArgumentException(String.format("User with ID %d already exists", user.getId()));
            if(!find(UserSpecification.byUsername(user.getUsername())).isEmpty())
                throw new ApplicationException(ApplicationException.ErrorCode.USERNAME_NOT_UNIQUE);
            user.setId(getNextID());
            users.add(user);
        }
    }

    @Override
    public void updateUser(User user) {
        synchronized (users) {
            int index = Math.toIntExact(user.getId());
            if (index == -1)
                throw new IllegalArgumentException(String.format("User with ID %d doesn't exist", user.getId()));
            users.set(index, user);
        }
    }

    @Override
    public void removeUser(Long id) {
        synchronized (users) {
            users.removeIf(user -> user.getId().equals(id));
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
