package com.project.softunisunset.service;

import com.project.softunisunset.models.dto.CreateStoryDTO;
import com.project.softunisunset.models.entity.Story;
import com.project.softunisunset.models.entity.User;
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
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class StoryServiceTest {
    @Mock
    private StoryRepository mockStoryRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private UserRepository mockUserRepository;

    @InjectMocks
    private StoryService storyService;

    @BeforeEach
    void setUp() {
        storyService = new StoryService(mockStoryRepository, mockModelMapper, mockUserRepository);
    }



    @Test
    void createStoryShouldReturnTrueWhenCreationIsSuccessful() {
        // Arrange
        CreateStoryDTO createStoryDTO = new CreateStoryDTO();
        createStoryDTO.setName("Test Story");
        createStoryDTO.setStory("This is a test story content.");

        String loggedInUsername = "testUser";

        // Mock SecurityContext and Authentication
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(loggedInUsername);
        SecurityContextHolder.setContext(securityContext);

        User currentUser = new User();
        currentUser.setUsername(loggedInUsername);
        when(mockUserRepository.findByUsername(loggedInUsername)).thenReturn(Optional.of(currentUser));

        Story mappedStory = new Story();
        when(mockModelMapper.map(createStoryDTO, Story.class)).thenReturn(mappedStory);

        // Act
        boolean result = storyService.createStory(createStoryDTO);

        // Assert
        assertTrue(result);
        assertEquals(currentUser, mappedStory.getUser());
        verify(mockStoryRepository, times(1)).save(mappedStory);
    }



    @Test
    void createStoryShouldReturnFalseWhenCurrentUserNotFound() {
        // Arrange
        CreateStoryDTO createStoryDTO = new CreateStoryDTO();
        createStoryDTO.setName("Test Story");
        createStoryDTO.setStory("This is a test story content.");


        String loggedInUsername = "nonexistentUser";

        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(loggedInUsername);
        SecurityContextHolder.setContext(securityContext);



        when(mockUserRepository.findByUsername(loggedInUsername)).thenReturn(Optional.empty());

        // Act
        boolean result = storyService.createStory(createStoryDTO);

        // Assert
        assertFalse(result);
        verify(mockStoryRepository, never()).save(any(Story.class));
    }
}
