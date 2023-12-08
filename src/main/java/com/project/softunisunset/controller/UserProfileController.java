package com.project.softunisunset.controller;

import com.project.softunisunset.models.entity.Sunset;
import com.project.softunisunset.models.entity.User;
import com.project.softunisunset.service.SunsetService;
import com.project.softunisunset.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserProfileController {

    private UserService userService;
    private SunsetService sunsetService;

    public UserProfileController(UserService userService, SunsetService sunsetService) {
        this.userService = userService;
        this.sunsetService = sunsetService;
    }

    @GetMapping("/profile")
    public String profile(Model model){
        User user = this.userService.getCurrentUser();
        model.addAttribute("user",user);


        List<Sunset> sunsets = this.sunsetService.getAllSunsetsByUser(user);

        model.addAttribute("feedSunsets",sunsets);
        return "profile";
    }

    @GetMapping("/profile/likes")
    public String likes(Model model){
        User user = this.userService.getCurrentUser();
        List<Sunset> sunsets = this.userService.getAllLikedSunsetsOfUser(user);
        model.addAttribute("sunsets", sunsets);


        return "liked-sunsets";
    }


}
