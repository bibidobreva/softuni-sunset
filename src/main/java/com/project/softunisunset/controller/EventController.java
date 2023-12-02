package com.project.softunisunset.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventController {

    @GetMapping("/events/add")
    public String eventsAdd(){
        return "events/add";
    }

    @GetMapping("/events")
    public String events(){
        return "events";
    }
}
