package com.jaba.webapp.service.user;

import com.jaba.webapp.domain.user.User;
import com.jaba.webapp.exceptions.ApplicationException;
import com.jaba.webapp.repository.specification.user.UserSpecification;
import com.jaba.webapp.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagerImpl implements  UserManager{

    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() { return userRepository.find(UserSpecification.all()); }

    @Override
    public User getUserById(Long id) {
        List<User> users = userRepository.find(UserSpecification.byId(id));
        return users.size() == 0 ? null : users.get(0);
    }

    @Override
    public void deleteUser(Long id) {
    }

    @Override
    public void addUser(User user) throws ApplicationException { userRepository.addUser(user); }

    @Override
    public void updateUser(User user)  { userRepository.updateUser(user); }


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
