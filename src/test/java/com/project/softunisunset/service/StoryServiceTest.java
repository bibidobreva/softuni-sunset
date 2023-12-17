package com.project.softunisunset.service;


import com.project.softunisunset.models.entity.User;
import com.project.softunisunset.models.entity.UserRoleEntity;
import com.project.softunisunset.models.enums.UserRolesEnums;
import com.project.softunisunset.repositories.StoryRepository;
import com.project.softunisunset.repositories.UserRepository;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class StoryServiceTest {

    @Mock
    private StoryRepository mockStoryRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private Authentication mockAuthentication;


    private StoryService storyService;

    @BeforeEach
    void setUp() {
        storyService = new StoryService(mockStoryRepository, mockModelMapper, mockUserRepository);
    }


    @Test
    void createStorySuccessful(){

        User testUser = createUser();
        when(mockUserRepository.findByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));
    }



    private static User createUser(){
        User user = new User();
        user.setUsername("test");
        user.setFirstName("testName");
        user.setLastName("lastName");
        user.setEmail("test@email.com");
        user.setPassword("passwordTest");

        UserRoleEntity role = new UserRoleEntity();
        UserRolesEnums admin = UserRolesEnums.ADMIN;
        role.setRole(admin);


        user.setRoles(List.of(role));
        return user;
    }


}
