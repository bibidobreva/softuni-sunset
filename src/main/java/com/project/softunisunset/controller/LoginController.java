package com.project.softunisunset.controller;

import com.project.softunisunset.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller

public class LoginController {
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        // Authenticate user and retrieve role
        String role = authenticateAndGetRole(username, password);

        // Set the authenticated user in the session

        //TODO
        LoggedUser user = new LoggedUser();
        user.setId(1); // Set user ID as needed
        user.setUsername(username);
        user.setRole(role);

        model.addAttribute("user", user);


        // Redirect based on role or to a default page

        //TODO
        if ("ADMIN".equals(role)) {
            return "redirect:/admin-page";
        } else {
            return "redirect:/user-page";
        }
    }

    private String authenticateAndGetRole(String username, String password) {
        // Implement your authentication logic and retrieve user role from a database or other source
        // For simplicity, let's assume a default role for all users
        return "USER";
    }
}
