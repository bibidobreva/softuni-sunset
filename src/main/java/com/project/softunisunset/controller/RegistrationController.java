package com.project.softunisunset.controller;

import com.project.softunisunset.models.dto.UserRegistrationDTO;
import com.project.softunisunset.service.AuthService;
import com.project.softunisunset.session.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




public class RegistrationController {


    private LoggedUser loggedUser;
    private AuthService authService;

    public RegistrationController(LoggedUser loggedUser, AuthService authService) {
        this.loggedUser = loggedUser;
        this.authService = authService;
    }


    @GetMapping("/register")
    public String register() {
        long loggedUserId = loggedUser.getId();
        if(loggedUserId!=0){
            return "redirect:/home";
        }
        return "register";
    }


    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO registrationDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()||!this.authService.register(registrationDTO)) {
            redirectAttributes.addFlashAttribute("registrationDTO", registrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registrationDTO", bindingResult);

            return "redirect:/register";
        }

        long loggedUserId = loggedUser.getId();
        if(loggedUserId!=0){
            return "redirect:/home";
        }

        return "redirect:login";
    }
}
