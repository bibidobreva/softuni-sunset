package com.project.softunisunset.controller;

import com.project.softunisunset.models.dto.CreateSunsetDTO;
import com.project.softunisunset.models.entity.Sunset;
import com.project.softunisunset.models.enums.ContinentName;
import com.project.softunisunset.repositories.SunsetRepository;
import com.project.softunisunset.service.SunsetService;
import com.project.softunisunset.session.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class SunsetController {
    private LoggedUser loggedUser;
    private final SunsetRepository sunsetRepository;
    private SunsetService sunsetService;

    public SunsetController(LoggedUser loggedUser, SunsetRepository sunsetRepository, SunsetService sunsetService) {
        this.loggedUser = loggedUser;
        this.sunsetRepository = sunsetRepository;
        this.sunsetService = sunsetService;
    }

    @ModelAttribute("createSunsetDTO")
    public CreateSunsetDTO initCreateSunsetDTO(){
        return new CreateSunsetDTO();
    }


    @GetMapping("/sunset/add")
    public String sunsets(){
        //TODO

        return "add-sunset";
    }

    @PostMapping("/sunset/add")
    public String sunsets(@Valid CreateSunsetDTO createSunsetDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()||!this.sunsetService.createSunset(createSunsetDTO)){
            redirectAttributes.addFlashAttribute("createSunsetDTO", createSunsetDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createSunsetDTO", bindingResult);

            return "redirect:/sunset/add";
        }
        return "redirect:/home";
    }


    @GetMapping("/sunset/continent/{continentName}")
    public String showSunsetsByContinent(@PathVariable String continentName, Model model) {
        try {
            ContinentName continent = ContinentName.valueOf(continentName.toUpperCase());
            List<Sunset> sunsets = sunsetService.getAllSunsetsByContinent(continent);

            model.addAttribute("sunsets", sunsets);
            model.addAttribute("continentName", continentName);

            return "continent";
        } catch (IllegalArgumentException e) {

            return "redirect:/error";
        }
    }

    @GetMapping("/sunset/continent")
    public String continents(){
        return "continent";
    }
}
