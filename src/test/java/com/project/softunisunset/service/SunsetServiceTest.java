package com.project.softunisunset.service;

import com.project.softunisunset.models.dto.CreateSunsetDTO;
import com.project.softunisunset.models.entity.Continent;
import com.project.softunisunset.models.entity.Sunset;
import com.project.softunisunset.models.entity.User;
import com.project.softunisunset.models.enums.ContinentName;
import com.project.softunisunset.repositories.ContinentRepository;
import com.project.softunisunset.repositories.SunsetRepository;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SunsetServiceTest {
    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private SunsetRepository mockSunsetRepository;

    @Mock
    private ContinentRepository mockContinentRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @InjectMocks
    private SunsetService sunsetService;

    @BeforeEach
    void setUp() {
        sunsetService = new SunsetService(mockUserRepository, mockSunsetRepository, mockContinentRepository, mockModelMapper);
    }


    @Test
    void createSunsetShouldReturnTrueWhenCreationIsSuccessful() throws IOException {
        // Arrange
        CreateSunsetDTO createSunsetDTO = new CreateSunsetDTO();
        createSunsetDTO.setContinent("EUROPE");

        MultipartFile file = mock(MultipartFile.class);
        byte[] fileBytes = "test photo".getBytes();
        when(file.getBytes()).thenReturn(fileBytes);


        String loggedInUsername = "testUser";
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(loggedInUsername);
        SecurityContextHolder.setContext(securityContext);

        ContinentName continentName = ContinentName.EUROPE;
        Continent testContinent = new Continent();
        testContinent.setName(continentName);
        Optional<Continent> continent = Optional.of(testContinent);

        User currentUser = new User();
        currentUser.setUsername(loggedInUsername);
        when(mockContinentRepository.findByName(continentName)).thenReturn(continent);
        when(mockUserRepository.findByUsername(loggedInUsername)).thenReturn(Optional.of(currentUser));
        when(mockModelMapper.map(createSunsetDTO, Sunset.class)).thenReturn(new Sunset());

        // Act
        boolean result = sunsetService.createSunset(createSunsetDTO, file);

        // Assert
        assertTrue(result);
        verify(mockSunsetRepository, times(1)).save(any(Sunset.class));
    }


    @Test
    void createSunsetShouldReturnFalseWhenUserNotFound() throws IOException {
        // Arrange
        CreateSunsetDTO createSunsetDTO = new CreateSunsetDTO();
        createSunsetDTO.setContinent("EUROPE");

        MultipartFile file = mock(MultipartFile.class);

        String loggedInUsername = "nonexistentUser";

        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(loggedInUsername);
        SecurityContextHolder.setContext(securityContext);

        when(mockUserRepository.findByUsername(loggedInUsername)).thenReturn(Optional.empty());

        ContinentName continentName = ContinentName.EUROPE;
        Continent testContinent = new Continent();
        testContinent.setName(continentName);

        when(mockContinentRepository.findByName(ContinentName.EUROPE)).thenReturn(Optional.of(testContinent));

        // Act
        boolean result = sunsetService.createSunset(createSunsetDTO, file);

        // Assert
        assertFalse(result);
        verify(mockSunsetRepository, never()).save(any(Sunset.class));
    }


    @Test
    void getAllSunsetsByContinentShouldReturnSunsets() {
        // Arrange
        ContinentName continentName = ContinentName.EUROPE;
        Continent testContinent = new Continent();
        testContinent.setName(continentName);
        Optional<Continent> continent = Optional.of(testContinent);
        when(mockContinentRepository.findByName(continentName)).thenReturn(continent);

        List<Sunset> sunsets = Collections.singletonList(new Sunset());
        when(mockSunsetRepository.findAllByContinent(continent.get())).thenReturn(sunsets);

        // Act
        List<Sunset> result = sunsetService.getAllSunsetsByContinent(continentName);

        // Assert
        assertEquals(sunsets, result);
    }



    @Test
    void getAllSunsetsByContinentShouldReturnEmptyListWhenContinentNotFound() {
        // Arrange
        ContinentName continentName = ContinentName.EUROPE;
        when(mockContinentRepository.findByName(continentName)).thenReturn(Optional.empty());

        // Act
        List<Sunset> result = sunsetService.getAllSunsetsByContinent(continentName);

        // Assert
        assertTrue(result.isEmpty());
    }


    @Test
    void getAllSunsetsShouldReturnAllSunsets() {
        // Arrange
        List<Sunset> allSunsets = Collections.singletonList(new Sunset());
        when(mockSunsetRepository.findAll()).thenReturn(allSunsets);

        // Act
        List<Sunset> result = sunsetService.getAllSunsets();

        // Assert
        assertEquals(allSunsets, result);
    }

    @Test
    void getAllSunsetsByUserShouldReturnUserSunsets() {
        // Arrange
        User user = new User();
        List<Sunset> userSunsets = Collections.singletonList(new Sunset());
        when(mockSunsetRepository.findAllByUser(user)).thenReturn(userSunsets);

        // Act
        List<Sunset> result = sunsetService.getAllSunsetsByUser(user);

        // Assert
        assertEquals(userSunsets, result);
    }
}
