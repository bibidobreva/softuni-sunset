package com.project.softunisunset.service;

import com.project.softunisunset.models.dto.UserRegistrationDTO;
import com.project.softunisunset.models.entity.Sunset;
import com.project.softunisunset.models.entity.User;
import com.project.softunisunset.models.entity.UserRoleEntity;
import com.project.softunisunset.models.enums.UserRolesEnums;
import com.project.softunisunset.repositories.StoryRepository;
import com.project.softunisunset.repositories.SunsetRepository;
import com.project.softunisunset.repositories.UserRepository;
import com.project.softunisunset.repositories.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final SunsetRepository sunsetRepository;
    private final StoryRepository storyRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    private final UserRoleRepository userRoleRepository;

    public UserService(UserRepository userRepository, SunsetRepository sunsetRepository, StoryRepository storyRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.sunsetRepository = sunsetRepository;
        this.storyRepository = storyRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
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

        UserRolesEnums userRolesEnums = null;

        if (userRepository.count() == 0) {

            userRolesEnums = UserRolesEnums.ADMIN;


        } else {
            userRolesEnums = UserRolesEnums.USER;
        }


        Optional<UserRoleEntity> role = userRoleRepository.findByRole(userRolesEnums);

        List<UserRoleEntity> roles = new ArrayList<>();
        roles.add(role.get());



        registrationDTO.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));


        User user = this.modelMapper.map(registrationDTO, User.class);
        user.setRoles(roles);

        this.userRepository.save(user);
        return true;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User getCurrentUser(){
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> currentUser = this.userRepository.findByUsername(loggedInUsername);
        if(currentUser.isEmpty()){
            throw new RuntimeException();
        }
        return currentUser.get();
    }

    public void likeSunset(User user, Long sunsetId) {
        Optional<Sunset> sunset = this.sunsetRepository.findById(sunsetId);
        if(sunset.isEmpty()){
            throw new RuntimeException();
        }
        user.getLikedSunsets().add(sunset.get());
        userRepository.save(user);
    }

    public List<Sunset> getAllLikedSunsetsOfUser(User user) {
        return user.getLikedSunsets();
    }
}
