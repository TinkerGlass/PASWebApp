package com.jaba.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Start {

    @GetMapping(value = "/")
    public String showHome() {
        return "home";
    }
}
