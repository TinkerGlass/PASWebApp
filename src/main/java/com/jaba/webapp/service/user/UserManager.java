package com.jaba.webapp.service.user;

import com.jaba.webapp.domain.user.User;
import com.jaba.webapp.exceptions.ApplicationException;

import java.util.List;

public interface UserManager {
    List<User> getAllUsers();
    User getUserById(Long id);
    void deleteUser(Long id);
    void addUser(User user) throws ApplicationException;
    void updateUser(User user);

}
