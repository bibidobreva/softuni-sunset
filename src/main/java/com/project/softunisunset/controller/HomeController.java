package com.project.softunisunset.controller;

import com.project.softunisunset.models.entity.Sunset;
import com.project.softunisunset.models.entity.User;
import com.project.softunisunset.service.SunsetService;
import com.project.softunisunset.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    private SunsetService sunsetService;
    private UserService userService;


    public HomeController(SunsetService sunsetService, UserService userService) {

        this.sunsetService = sunsetService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String loggedOutIndex(){



        return "index";
    }


    @GetMapping("/home")
    public String LoggedIndex(Model model){


        List<Sunset> sunsets = this.sunsetService.getAllSunsets();
        User user = this.userService.getCurrentUser();

        model.addAttribute("feedSunsets",sunsets);
        model.addAttribute("user", user);

        return "home";
    }

    @GetMapping({"/likeSunset/{sunsetId}"})
    public String likeSunset(@PathVariable Long sunsetId) {
        User user = this.userService.getCurrentUser();
        this.userService.likeSunset(user, sunsetId);



        return "redirect:/home";
    }
}
