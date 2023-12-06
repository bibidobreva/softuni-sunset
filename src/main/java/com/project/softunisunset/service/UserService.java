package com.project.softunisunset.service;

import com.project.softunisunset.models.dto.UserRegistrationDTO;
import com.project.softunisunset.models.entity.User;
import com.project.softunisunset.repositories.StoryRepository;
import com.project.softunisunset.repositories.SunsetRepository;
import com.project.softunisunset.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    public boolean register(UserRegistrationDTO registrationDTO) {
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            return false;
        }

        Optional<User> byEmail = this.userRepository.findByEmail(registrationDTO.getEmail());
        Optional<User> byUsername = this.userRepository.findByUsername(registrationDTO.getUsername());

        if (byEmail.isPresent() || byUsername.isPresent()) {
            return false;
        }

//        User user = new User();
//        user.setUsername(registrationDTO.getUsername());
//        user.setEmail(registrationDTO.getEmail());
//        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

//TODO
        registrationDTO.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));


        this.userRepository.save(this.modelMapper.map(registrationDTO, User.class));
        return true;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
