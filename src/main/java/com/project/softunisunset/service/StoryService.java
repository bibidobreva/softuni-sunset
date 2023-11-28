package com.project.softunisunset.service;

import com.project.softunisunset.models.dto.CreateStoryDTO;
import com.project.softunisunset.models.entity.Story;
import com.project.softunisunset.models.entity.User;
import com.project.softunisunset.repositories.StoryRepository;
import com.project.softunisunset.repositories.UserRepository;
import com.project.softunisunset.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoryService {
    private final StoryRepository storyRepository;
    private final ModelMapper modelMapper;
    private LoggedUser loggedUser;
    private final UserRepository userRepository;


    public StoryService(StoryRepository storyRepository, ModelMapper modelMapper, LoggedUser loggedUser, UserRepository userRepository) {
        this.storyRepository = storyRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }


    public boolean createStory(CreateStoryDTO createStoryDTO){
        Story story = new Story();

        //to check user
        Optional<User> currentUser = userRepository.findById(loggedUser.getId());

        if(currentUser.isEmpty()){
            return false;
        }

        this.storyRepository.save(this.modelMapper.map(createStoryDTO, Story.class));

        return true;
    }
}
