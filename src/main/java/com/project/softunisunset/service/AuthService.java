package com.project.softunisunset.service;

import com.project.softunisunset.models.dto.UserRegistrationDTO;
import com.project.softunisunset.models.entity.User;
import com.project.softunisunset.repositories.UserRepository;
import com.project.softunisunset.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private LoggedUser loggedUser;
    private final ModelMapper modelMapper;

    public AuthService(UserRepository userRepository, LoggedUser loggedUser, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
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



        this.userRepository.save(this.modelMapper.map(registrationDTO, User.class));
        return true;
    }
}
