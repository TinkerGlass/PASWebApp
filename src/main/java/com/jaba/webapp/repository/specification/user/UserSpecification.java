package com.jaba.webapp.repository.specification.user;

import com.jaba.webapp.domain.user.User;
import com.jaba.webapp.repository.specification.Specification;

public class UserSpecification {
    public static Specification<User> all() {
        return user -> true;
    }

    public static Specification<User> byId(Long id) {
        return user -> user.getId().equals(id);
    }

    public static Specification<User> byUsername(String username) {
        return user -> user.getUsername().equals(username);
    }

}
