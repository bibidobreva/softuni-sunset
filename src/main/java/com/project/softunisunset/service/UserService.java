package com.project.softunisunset.service;

import com.project.softunisunset.models.dto.UserRegistrationDTO;
import com.project.softunisunset.models.entity.User;
import com.project.softunisunset.repositories.StoryRepository;
import com.project.softunisunset.repositories.SunsetRepository;
import com.project.softunisunset.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final SunsetRepository sunsetRepository;
    private final StoryRepository storyRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, SunsetRepository sunsetRepository, StoryRepository storyRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.sunsetRepository = sunsetRepository;
        this.storyRepository = storyRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    public void register(UserRegistrationDTO userRegistrationDTO){
        userRegistrationDTO.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        this.userRepository.save(this.modelMapper.map(userRegistrationDTO, User.class));
    }

    //TODO
    public void assignRole(Long userId, String role) {
    }
}
