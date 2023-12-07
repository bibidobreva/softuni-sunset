package com.project.softunisunset.controller;

import com.project.softunisunset.models.entity.Sunset;
import com.project.softunisunset.service.SunsetService;
import com.project.softunisunset.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private SunsetService sunsetService;


    public HomeController( SunsetService sunsetService) {

        this.sunsetService = sunsetService;
    }

    @GetMapping("/")
    public String loggedOutIndex(){



        return "index";
    }


    @GetMapping("/home")
    public String LoggedIndex(Model model){


        List<Sunset> sunsets = this.sunsetService.getAllSunsets();

        model.addAttribute("feedSunsets",sunsets);

        return "home";
    }
}
