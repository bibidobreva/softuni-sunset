package com.project.softunisunset.controller;

import com.project.softunisunset.models.dto.ChangeUsernameDTO;
import com.project.softunisunset.models.entity.Sunset;
import com.project.softunisunset.models.entity.User;
import com.project.softunisunset.service.SunsetService;
import com.project.softunisunset.service.UserService;
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
public class UserProfileController {

    private UserService userService;
    private SunsetService sunsetService;

    public UserProfileController(UserService userService, SunsetService sunsetService) {
        this.userService = userService;
        this.sunsetService = sunsetService;
    }

    @ModelAttribute("changeUsernameDTO")
    public ChangeUsernameDTO initChangeUsernameDTO(){
        return  new ChangeUsernameDTO();
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        User user = this.userService.getCurrentUser();
        model.addAttribute("user", user);


        List<Sunset> sunsets = this.sunsetService.getAllSunsetsByUser(user);

        model.addAttribute("feedSunsets", sunsets);
        return "profile";
    }

    @GetMapping("/profile/likes")
    public String likes(Model model) {
        User user = this.userService.getCurrentUser();
        List<Sunset> sunsets = this.userService.getAllLikedSunsetsOfUser(user);
        model.addAttribute("sunsets", sunsets);


        return "liked-sunsets";
    }


    @GetMapping("/username")
    public String changeUsername() {
        return "username";
    }

    @PostMapping("/username")
    public String changeUsername(@Valid ChangeUsernameDTO changeUsernameDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        User user = userService.getCurrentUser();
        if (bindingResult.hasErrors() || !this.userService.changeUsername(changeUsernameDTO, user)) {
            redirectAttributes.addFlashAttribute("changeUsernameDTO", changeUsernameDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.changeUsernameDTO", bindingResult);
            model.addAttribute("bad_credentials", true);

            return "username";
        }

        return "redirect:/profile";
    }


}
