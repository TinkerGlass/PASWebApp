package com.jaba.webapp.controller;

import com.jaba.webapp.domain.user.ClientUser;
import com.jaba.webapp.domain.user.User;
import com.jaba.webapp.service.user.UserManager;
import com.jaba.webapp.service.user.UserManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Arrays;
import java.util.List;

@ApplicationScope
@Controller
public class UserController {

    private UserManager userService;

    @Autowired
    public UserController(UserManager userService){
        this.userService = userService;
    }

    @ModelAttribute("allUsers")
    public List<User> populateUsers() {
        return userService.getAllUsers();
    }

    @ModelAttribute("allAccountTypes")
    public List<User.AccountType> populateAccountTypes() {
        return Arrays.asList(User.AccountType.values());
    }

    @RequestMapping(value = "/users")
    public String showUsers() {
        return "users";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser(@ModelAttribute ("user") User user) {
        return "addUser";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute ("user") User user, final BindingResult bindingResult, final ModelMap model) {
        return "addUser";
    }

}
