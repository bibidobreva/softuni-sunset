package com.project.softunisunset.service;

import com.project.softunisunset.models.dto.CreateStoryDTO;
import com.project.softunisunset.models.entity.Story;
import com.project.softunisunset.repositories.StoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class StoryService {
    private final StoryRepository storyRepository;
    private final ModelMapper modelMapper;


    public StoryService(StoryRepository storyRepository, ModelMapper modelMapper) {
        this.storyRepository = storyRepository;
        this.modelMapper = modelMapper;
    }


    public boolean create(CreateStoryDTO createStoryDTO){
        Story story = new Story();

        //to check user


        this.storyRepository.save(this.modelMapper.map(createStoryDTO, Story.class));

        return true;
    }
}
