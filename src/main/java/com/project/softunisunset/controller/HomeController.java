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
    private LoggedUser loggedUser;
    private SunsetService sunsetService;


    public HomeController(LoggedUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    @GetMapping("/")
    public String loggedOutIndex(){

        //TODO
//        long loggedUserId = this.loggedUser.getId();
//
//
//        if(loggedUserId!=0){
//            return "redirect:/home";
//        }
        return "index";
    }


    @GetMapping("/home")
    public String LoggedIndex(Model model){
        long loggedUserId = this.loggedUser.getId();
        if(loggedUserId==0){
            return "redirect:/";
        }

        List<Sunset> sunsets = this.sunsetService.getAllSunsets();

        model.addAttribute("feedSunsets",sunsets);

        return "home";
    }
}
