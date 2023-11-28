package com.project.softunisunset.service;

import com.project.softunisunset.repositories.StoryRepository;
import com.project.softunisunset.repositories.SunsetRepository;
import com.project.softunisunset.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final SunsetRepository sunsetRepository;
    private final StoryRepository storyRepository;

    public UserService(UserRepository userRepository, SunsetRepository sunsetRepository, StoryRepository storyRepository) {
        this.userRepository = userRepository;
        this.sunsetRepository = sunsetRepository;
        this.storyRepository = storyRepository;
    }

    //TODO
    public void assignRole(Long userId, String role) {
    }
}
