package com.jaba.webapp.controller;

import com.jaba.webapp.controller.breadcrumbs.Link;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Link(label="home.title", depth=0)
    @RequestMapping(value = "/")
    public String showHome() {
        return "home";
    }

}
