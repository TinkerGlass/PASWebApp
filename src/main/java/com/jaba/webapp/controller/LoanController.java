package com.jaba.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoanController {

    @GetMapping(value = "/loans")
    public String showCustomers() {
        return "loan";
    }
}
