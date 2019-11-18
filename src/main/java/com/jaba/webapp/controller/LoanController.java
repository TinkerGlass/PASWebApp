package com.jaba.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoanController {



    @RequestMapping(value = "/loans")
    public String openLoans() {
        return "loan";
    }
}
