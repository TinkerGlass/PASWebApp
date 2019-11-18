package com.jaba.webapp.controller;

import com.jaba.webapp.domain.user.User;
import com.jaba.webapp.service.UserManagerImpl;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private UserManagerImpl userService;

    @Autowired
    public UserController(UserManagerImpl userService){
        this.userService = userService;
    }

    @GetMapping(value = "/customers")
    public String showCustomers(Model model) {
        List<User> arrayList = userService.getAllUsers();
        model.addAttribute("users", arrayList);;
        return "users";
    }
}
