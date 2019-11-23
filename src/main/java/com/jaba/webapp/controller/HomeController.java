package com.jaba.webapp.controller;

import com.jaba.webapp.breadcrumbs.annotation.Breadcrumb;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Breadcrumb(label="home.title", depth=0, family={"home"})
    @RequestMapping(value = "/")
    public String showHome() {
        return "home";
    }

}
