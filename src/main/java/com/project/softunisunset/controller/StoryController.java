package com.project.softunisunset.controller;

import com.project.softunisunset.models.dto.CreateStoryDTO;
import com.project.softunisunset.models.entity.Story;
import com.project.softunisunset.repositories.StoryRepository;
import com.project.softunisunset.service.StoryService;
import com.project.softunisunset.session.LoggedUser;
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
public class StoryController {
    private LoggedUser loggedUser;
    private StoryService storyService;
    private StoryRepository storyRepository;

    public StoryController(LoggedUser loggedUser, StoryService storyService, StoryRepository storyRepository) {
        this.loggedUser = loggedUser;
        this.storyService = storyService;
        this.storyRepository = storyRepository;
    }

    @ModelAttribute("createStoryDTO")
    public CreateStoryDTO initCreateStoryDTO(){
        return new CreateStoryDTO();
    }

    @GetMapping("/blog")
    public String stories(Model model){
        //TODO

        List<Story> storyList = this.storyRepository.findAll();
        model.addAttribute("storyList",storyList);

        return "blog";
    }

    @PostMapping("/blog")
    public String storiesAdd(@Valid CreateStoryDTO createStoryDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){

        if(bindingResult.hasErrors()||!this.storyService.createStory(createStoryDTO)){
            redirectAttributes.addFlashAttribute("createStoryDTO", createStoryDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createStoryDTO", bindingResult);

            List<Story> storyList = this.storyRepository.findAll();
            model.addAttribute("storyList", storyList);

            return "redirect:/blog";
        }


        List<Story> storyList = this.storyRepository.findAll();
        model.addAttribute("storyList",storyList);
        return "redirect:/blog";
    }
}
