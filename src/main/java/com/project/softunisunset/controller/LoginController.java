package com.project.softunisunset.controller;


import com.project.softunisunset.models.entity.User;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller


public class LoginController {
    @GetMapping("/login")
    public String login(Model model) {

        return "login";
    }

    @PostMapping("/login-error")
    public String onFailure(
            @ModelAttribute("username") String username, Model model) {


        model.addAttribute("username", username);
        model.addAttribute("bad_credentials", true);


        return "login";
    }


}
