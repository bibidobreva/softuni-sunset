package com.project.softunisunset.controller;

import com.project.softunisunset.models.dto.CreateEventDTO;
import com.project.softunisunset.models.entity.Event;
import com.project.softunisunset.service.EventService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @ModelAttribute("createEventDTO")
    public CreateEventDTO initRegistrationDTO(){
        return new CreateEventDTO();
    }
    @GetMapping("/events/add")
    public String eventsAdd(){
        return "add-event";
    }



    @PostMapping("/events/add")
    public String register(@Valid CreateEventDTO createEventDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()||!this.eventService.createEvent(createEventDTO)) {
            redirectAttributes.addFlashAttribute("createEventDTO", createEventDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createEventDTO", bindingResult);

            return "redirect:/events/add";
        }



        return "redirect:/events";
    }





    @GetMapping("/events")
    public String events(Model model){

        List<Event> eventList =eventService.getAllEvents();
        model.addAttribute("eventList",eventList);
        return "events";
    }
}
