package com.project.softunisunset.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller

public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    public String onFailure(
            @ModelAttribute("username") String email, Model model) {

        model.addAttribute("email", email);
        model.addAttribute("bad_credentials", "true");

        return "login-error";
    }
}
