package com.jaba.webapp.controller;

import org.mapstruct.Named;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @GetMapping(value = "/customers")
    public String showCustomers(Model model) {
        model.addAttribute("message", "dzialam");
        return "users";
    }
}
