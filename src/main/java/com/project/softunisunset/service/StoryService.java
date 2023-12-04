package com.project.softunisunset.service;

import com.project.softunisunset.models.dto.CreateStoryDTO;
import com.project.softunisunset.models.entity.Story;
import com.project.softunisunset.models.entity.User;
import com.project.softunisunset.repositories.StoryRepository;
import com.project.softunisunset.repositories.UserRepository;
import com.project.softunisunset.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
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



        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> currentUser = this.userRepository.findByUsername(loggedInUsername);

        if(currentUser.isEmpty()){
            return false;
        }

        Story userStory =this.modelMapper.map(createStoryDTO, Story.class);
        userStory.setUser(currentUser.get());

        this.storyRepository.save(userStory);

        return true;
    }
}
