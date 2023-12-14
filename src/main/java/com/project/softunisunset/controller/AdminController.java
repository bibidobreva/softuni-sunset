package com.project.softunisunset.controller;

import com.project.softunisunset.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/changeUserRole")
    public String changeUserRole(Model model) {
        // Retrieve a list of users to populate the dropdown
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @PostMapping("/changeUserRole")
    public String changeUserRole(@RequestParam String username, @RequestParam String newRole) {

        userService.changeUserRole(username, newRole);
        return "redirect:/changeUserRole";
    }
}
