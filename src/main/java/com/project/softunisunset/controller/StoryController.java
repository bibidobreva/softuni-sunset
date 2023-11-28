package com.project.softunisunset.controller;

import com.project.softunisunset.models.dto.CreateStoryDTO;
import com.project.softunisunset.repositories.StoryRepository;
import com.project.softunisunset.service.StoryService;
import com.project.softunisunset.session.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("stories/add")
    public String stories(){
        //TODO

        return "story-add";
    }

    @PostMapping("/words/add")
    public String words(@Valid CreateStoryDTO createStoryDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()||!this.storyService.createStory(createStoryDTO)){
            redirectAttributes.addFlashAttribute("createStoryDTO", createStoryDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createStoryDTO", bindingResult);

            return "redirect:/stories/add";
        }
        return "redirect:/home";
    }
}
