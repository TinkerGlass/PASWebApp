package com.jaba.webapp.controller;

import com.jaba.webapp.breadcrumbs.annotation.Breadcrumb;
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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class RegisterController {

    private UserManager userService;

    @Breadcrumb(label="register.title", depth=0, family = {"register"})
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(@ModelAttribute User user) {
        return "register";
    }

    @Breadcrumb(label="register.title", depth=0, family = {"register"})
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute User user,
                          final BindingResult bindingResult,
                          @ModelAttribute("errors") ArrayList<ApplicationException> errors){
        if(bindingResult.hasErrors()) {
            return "register";
        }
        try {
            userService.addUser(user);
        } catch(ApplicationException e) {
            errors.add(e);
            if(e.getErrorCode() == ApplicationException.ErrorCode.USERNAME_NOT_UNIQUE.ordinal())
                bindingResult.addError(new FieldError("user", "username",""));
            user.setPasswordHash("");
            return "register";
        }
        return "redirect:/";
    }

    @Autowired
    public void setUserService(UserManager userService) {
        this.userService = userService;
    }
}
