package com.project.softunisunset.controller;

import com.project.softunisunset.models.dto.CreateSunsetDTO;
import com.project.softunisunset.models.entity.Sunset;
import com.project.softunisunset.models.enums.ContinentName;
import com.project.softunisunset.repositories.SunsetRepository;
import com.project.softunisunset.service.SunsetService;
import com.project.softunisunset.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SunsetController {

    private SunsetService sunsetService;

    public SunsetController( SunsetService sunsetService) {

        this.sunsetService = sunsetService;
    }

    @ModelAttribute("createSunsetDTO")
    public CreateSunsetDTO initCreateSunsetDTO(){
        return new CreateSunsetDTO();
    }


    @GetMapping("/sunset/add")
    public String sunsets(){


        return "add-sunset";
    }

    @PostMapping("/sunset/add")
    public String sunsets(@RequestParam("photo") MultipartFile photo, @Valid CreateSunsetDTO createSunsetDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        System.out.println("Received file: " + photo.getOriginalFilename());
        if(bindingResult.hasErrors()||!this.sunsetService.createSunset(createSunsetDTO, photo)){
            redirectAttributes.addFlashAttribute("createSunsetDTO", createSunsetDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createSunsetDTO", bindingResult);

            return "redirect:/sunset/add";
        }
        return "redirect:/home";
    }





    @GetMapping("/api/photos")
    @ResponseBody
    public ResponseEntity<List<String>> getPhotosByContinent(@RequestParam String continent) {
        try {
            ContinentName continentName = ContinentName.valueOf(continent.toUpperCase());
            List<Sunset> sunsets = sunsetService.getAllSunsetsByContinent(continentName);

            // Convert byte[] to Base64-encoded strings
            List<String> photoUrls = sunsets.stream()
                    .map(sunset -> "data:image/png;base64," + Base64.getEncoder().encodeToString(sunset.getPhoto()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(photoUrls);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }




    @GetMapping("/sunset/continent")
    public String continents(){
        return "continent";
    }





}
