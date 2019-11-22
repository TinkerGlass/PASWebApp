package com.jaba.webapp.controller;

import com.jaba.webapp.domain.user.User;
import com.jaba.webapp.exceptions.ApplicationException;
import com.jaba.webapp.service.user.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.validation.Valid;
import java.util.ArrayList;
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
    public String addUser(@RequestParam(value = "type", defaultValue = "client") User user, Model model) {
        model.addAttribute("user", user);
        return "addUser";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute User user, final BindingResult bindingResult, @ModelAttribute("errors") ArrayList<ApplicationException> errors){
        if(bindingResult.hasErrors())
            return "addUser";
        try {
            userService.addUser(user);
        } catch(ApplicationException e) {
            errors.add(e);
            bindingResult.addError(new FieldError("user", "username",""));
            return "addUser";
        }
        return "redirect:/users";
    }

    public static final String usernameNotUniqueMsg = "uNu";
}
