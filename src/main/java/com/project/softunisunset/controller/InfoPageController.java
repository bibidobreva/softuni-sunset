package com.project.softunisunset.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoPageController {

    @GetMapping("/contacts")
    public String contacts(){
        return "contact";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }


}
