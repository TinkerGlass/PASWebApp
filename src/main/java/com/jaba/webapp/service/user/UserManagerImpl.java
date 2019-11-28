package com.jaba.webapp.service.user;

import com.jaba.webapp.domain.user.User;
import com.jaba.webapp.exceptions.ApplicationException;
import com.jaba.webapp.repository.specification.user.UserSpecification;
import com.jaba.webapp.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.INTERFACES)
public class UserManagerImpl implements  UserManager{

    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() { return userRepository.find(UserSpecification.all()); }

    @Override
    public List<User> getAllActiveUsers() {
        return userRepository.find(UserSpecification.allActive());
    }

    @Override
    public User getUserById(Long id) {
        List<User> users = userRepository.find(UserSpecification.byId(id));
        return users.size() == 0 ? null : users.get(0);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.removeUser(id);
    }

    @Override
    public void addUser(User user) throws ApplicationException { userRepository.addUser(user); }

    @Override
    public void updateUser(User user)  { userRepository.updateUser(user); }

    @Override
    public void blockUser(Long userId) throws ApplicationException {
        List<User> users = userRepository.find(UserSpecification.byId(userId));
        if(users.isEmpty())
            throw new ApplicationException(ApplicationException.ErrorCode.USER_ID_DOESNT_EXIST);

        users.get(0).setActive(false);
        userRepository.updateUser(users.get(0));
    }

    @Override
    public void unblockUser(Long userId) throws ApplicationException {
        List<User> users = userRepository.find(UserSpecification.byId(userId));
        if(users.isEmpty())
            throw new ApplicationException(ApplicationException.ErrorCode.USER_ID_DOESNT_EXIST);

        users.get(0).setActive(true);
        userRepository.updateUser(users.get(0));
    }

    @Override
    public List<User> findUsersByUsername(String query) {
        return userRepository.find(user -> user.getUsername().toLowerCase().contains(query.toLowerCase()));
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
