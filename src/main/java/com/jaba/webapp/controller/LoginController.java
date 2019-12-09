package com.jaba.webapp.controller;

import com.jaba.webapp.domain.user.User;
import com.jaba.webapp.service.user.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@ApplicationScope
@Controller
public class LoginController {

    private UserManager userService;

    @Autowired
    public LoginController(UserManager userService){
        this.userService = userService;
    }


    @RequestMapping(value = "/logUser", method = RequestMethod.POST)
    public String addUser(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.getUserByName(username);
        if(user != null){
            if(user.getPasswordHash().equals(password)){
                return "redirect:/products";
            }
            else{
                return "redirect:/home";
            }
        }
        return "redirect:/home";
    }
}
