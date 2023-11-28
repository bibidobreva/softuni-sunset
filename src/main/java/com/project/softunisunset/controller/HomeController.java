package com.project.softunisunset.controller;

import com.project.softunisunset.session.LoggedUser;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {
    private LoggedUser loggedUser;

    public HomeController(LoggedUser loggedUser) {
        this.loggedUser = loggedUser;
    }
}
