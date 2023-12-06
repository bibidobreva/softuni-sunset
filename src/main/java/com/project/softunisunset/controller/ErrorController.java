package com.project.softunisunset.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
    @RequestMapping("/errors")
    public String showErrorPage() {
        return "error/errors";
    }

    @GetMapping("/errors")
    public String error(){
        return "error/errors";
    }
}
