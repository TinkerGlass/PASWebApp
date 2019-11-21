package com.jaba.webapp.service.user;

import com.jaba.webapp.domain.user.User;

import java.util.List;

public interface UserManager {
    List<User> getAllUsers();
    User getUserById(Long id);
    void deleteUser(Long id);
    void addUser(User user);
    void updateUser(User user);

}
