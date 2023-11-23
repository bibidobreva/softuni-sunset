package com.project.softunisunset.service;

import com.project.softunisunset.repositories.StoryRepository;
import org.springframework.stereotype.Service;

@Service
public class StoryService {
    private final StoryRepository storyRepository;


    public StoryService(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }


    
}
