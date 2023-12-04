package com.project.softunisunset.controller;

import com.project.softunisunset.models.dto.UserRegistrationDTO;
import com.project.softunisunset.service.UserService;
import com.project.softunisunset.session.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class RegistrationController {


    private LoggedUser loggedUser;
    private UserService userService;

    public RegistrationController(LoggedUser loggedUser, UserService authService) {
        this.loggedUser = loggedUser;
        this.userService = authService;
    }

    @ModelAttribute("userRegistrationDTO")
    public UserRegistrationDTO initRegistrationDTO(){
        return  new UserRegistrationDTO();
    }

    @GetMapping("/register")
    public String register() {
//        long loggedUserId = loggedUser.getId();

//        //TODO do i need that
//        if(loggedUserId!=0){
//            return "redirect:/home";
//        }
        return "register";
    }


    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO registrationDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()||!this.userService.register(registrationDTO)) {
            redirectAttributes.addFlashAttribute("registrationDTO", registrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registrationDTO", bindingResult);

            return "redirect:/register";
        }

//        long loggedUserId = loggedUser.getId();
//        if(loggedUserId!=0){
//            return "redirect:/home";
//        }

        return "redirect:login";
    }


//    @GetMapping("/logout")
//    public String logout(){
//        this.authService.logout();
//
//        return "redirect:/";
//    }
}
