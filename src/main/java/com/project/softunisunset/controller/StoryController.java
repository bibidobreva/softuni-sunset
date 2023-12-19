package com.project.softunisunset.controller;

import com.project.softunisunset.models.dto.CreateStoryDTO;
import com.project.softunisunset.models.entity.Story;
import com.project.softunisunset.repositories.StoryRepository;
import com.project.softunisunset.service.StoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class StoryController {

    private StoryService storyService;
    private StoryRepository storyRepository;

    public StoryController( StoryService storyService, StoryRepository storyRepository) {

        this.storyService = storyService;
        this.storyRepository = storyRepository;
    }

    @ModelAttribute("createStoryDTO")
    public CreateStoryDTO initCreateStoryDTO(){
        return new CreateStoryDTO();
    }

    @GetMapping("/blog")
    public String stories(Model model){

        loadStories(model);

        return "blog";
    }

    @PostMapping("/blog/add")
    public String storiesAdd(@Valid CreateStoryDTO createStoryDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){

        createStoryDTO.setDate(LocalDate.now());
        if(bindingResult.hasErrors()||!this.storyService.createStory(createStoryDTO)){
            redirectAttributes.addFlashAttribute("createStoryDTO", createStoryDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createStoryDTO", bindingResult);


            loadStories(model);

            return "redirect:/blog";
        }


        loadStories(model);


        return "redirect:/blog";
    }

    private void loadStories(Model model) {
        List<Story> storyList = this.storyRepository.findAllByOrderByIdDesc();
        model.addAttribute("storyList",storyList);
    }
}
