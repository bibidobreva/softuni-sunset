package com.project.softunisunset.service;

import com.project.softunisunset.models.dto.ChangeUsernameDTO;
import com.project.softunisunset.models.dto.UserRegistrationDTO;
import com.project.softunisunset.models.entity.Sunset;
import com.project.softunisunset.models.entity.User;
import com.project.softunisunset.models.entity.UserRoleEntity;
import com.project.softunisunset.models.enums.UserRolesEnums;
import com.project.softunisunset.repositories.SunsetRepository;
import com.project.softunisunset.repositories.UserRepository;
import com.project.softunisunset.repositories.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private UserRepository mockUserRepository;
    private SunsetRepository mockSunsetRepository;
    private ModelMapper mockModelMapper;
    private PasswordEncoder mockPasswordEncoder;
    private UserRoleRepository mockUserRoleRepository;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        mockUserRepository = mock(UserRepository.class);
        mockSunsetRepository = mock(SunsetRepository.class);
        mockModelMapper = mock(ModelMapper.class);
        mockPasswordEncoder = mock(PasswordEncoder.class);
        mockUserRoleRepository = mock(UserRoleRepository.class);
        userService = new UserService(mockUserRepository, mockSunsetRepository, mockModelMapper, mockPasswordEncoder, mockUserRoleRepository);
    }

    @Test
    void registerShouldReturnTrueWhenRegistrationIsSuccessful() {
        // Arrange
        UserRegistrationDTO registrationDTO = new UserRegistrationDTO();
        registrationDTO.setEmail("test@example.com");
        registrationDTO.setUsername("testUser");
        registrationDTO.setPassword("password");
        registrationDTO.setConfirmPassword("password");

        when(mockUserRepository.findByEmail(registrationDTO.getEmail())).thenReturn(Optional.empty());
        when(mockUserRepository.findByUsername(registrationDTO.getUsername())).thenReturn(Optional.empty());
        when(mockUserRepository.count()).thenReturn(0L);

        UserRoleEntity role = new UserRoleEntity();
        role.setRole(UserRolesEnums.ADMIN);
        when(mockUserRoleRepository.findByRole(UserRolesEnums.ADMIN)).thenReturn(Optional.of(role));

        when(mockPasswordEncoder.encode(registrationDTO.getPassword())).thenReturn("encodedPassword");
        when(mockModelMapper.map(registrationDTO, User.class)).thenReturn(new User());

        // Act
        boolean result = userService.register(registrationDTO);

        // Assert
        assertTrue(result);
        verify(mockUserRepository, times(1)).save(any());
    }


    @Test
    void getAllUsersShouldReturnAllUsers() {
        // Arrange
        User user1 = new User();
        User user2 = new User();
        when(mockUserRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        // Act
        List<User> result = userService.getAllUsers();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(user1));
        assertTrue(result.contains(user2));
        verify(mockUserRepository, times(1)).findAll();
    }


    @Test
    void getCurrentUserShouldReturnCurrentUser() {
        // Arrange
        String loggedInUsername = "testUser";
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(loggedInUsername);

        SecurityContextHolder.setContext(securityContext);

        User expectedUser = new User();
        when(mockUserRepository.findByUsername(loggedInUsername)).thenReturn(Optional.of(expectedUser));


        // Act
        User result = userService.getCurrentUser();

        // Assert
        assertEquals(expectedUser, result);
        verify(mockUserRepository, times(1)).findByUsername(loggedInUsername);
    }


    @Test
    void getCurrentUserShouldThrowRuntimeExceptionWhenUserNotFound() {
        // Arrange
        String loggedInUsername = "nonexistentUser";
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);

        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(loggedInUsername);

        when(mockUserRepository.findByUsername(loggedInUsername)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> userService.getCurrentUser());
        verify(mockUserRepository, times(1)).findByUsername(loggedInUsername);
    }


    @Test
    void likeSunsetShouldAddSunsetToLikedSunsets() {
        // Arrange
        User user = new User(); // Create a user instance (you may need to set up more details depending on your User class)
        Long sunsetId = 123L;
        Sunset sunset = new Sunset(); // Create a sunset instance (you may need to set up more details depending on your Sunset class)

        when(mockSunsetRepository.findById(sunsetId)).thenReturn(Optional.of(sunset));

        // Act
        userService.likeSunset(user, sunsetId);

        // Assert
        assertTrue(user.getLikedSunsets().contains(sunset));
        verify(mockUserRepository, times(1)).save(user);
    }

    @Test
    void likeSunsetShouldThrowRuntimeExceptionWhenSunsetNotFound() {
        // Arrange
        User user = new User(); // Create a user instance (you may need to set up more details depending on your User class)
        Long sunsetId = 123L;

        when(mockSunsetRepository.findById(sunsetId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> userService.likeSunset(user, sunsetId));
        verify(mockUserRepository, never()).save(any(User.class));
    }


    @Test
    void getAllLikedSunsetsOfUserShouldReturnLikedSunsets() {
        // Arrange


        // Create a user with liked sunsets
        User user = new User();
        List<Sunset> likedSunsets = new ArrayList<>();
        likedSunsets.add(new Sunset());
        likedSunsets.add(new Sunset());
        user.setLikedSunsets(likedSunsets);

        // Act
        List<Sunset> result = userService.getAllLikedSunsetsOfUser(user);

        // Assert
        assertEquals(likedSunsets, result);
    }


    @Test
    void changeUsernameShouldChangeUsernameSuccessfully() {
        // Arrange


        // Create a user with a current username
        User user = new User();
        user.setUsername("oldUsername");

        // Create a DTO with a new username
        ChangeUsernameDTO changeUsernameDTO = new ChangeUsernameDTO();
        changeUsernameDTO.setUsername("newUsername");

        // Mock the userRepository behavior
        when(mockUserRepository.findByUsername(changeUsernameDTO.getUsername())).thenReturn(Optional.empty());
        when(mockUserRepository.save(user)).thenReturn(user);


        // Act
        boolean result = userService.changeUsername(changeUsernameDTO, user);

        // Assert
        assertTrue(result);
        assertEquals(changeUsernameDTO.getUsername(), user.getUsername());
    }

    @Test
    void changeUsernameShouldFailDueToExistingUsername() {
        // Arrange


        // Create a user with a current username
        User user = new User();
        user.setUsername("oldUsername");

        // Create a DTO with an existing username
        ChangeUsernameDTO changeUsernameDTO = new ChangeUsernameDTO();
        changeUsernameDTO.setUsername("existingUsername");

        // Mock the userRepository behavior to return a user with the same username
        when(mockUserRepository.findByUsername(changeUsernameDTO.getUsername()))
                .thenReturn(Optional.of(new User()));


        // Act
        boolean result = userService.changeUsername(changeUsernameDTO, user);

        // Assert
        assertFalse(result);
        assertNotEquals(changeUsernameDTO.getUsername(), user.getUsername());
    }


    @Test
    void changeUserRoleShouldChangeRoleSuccessfully() {


        User user = new User();
        UserRoleEntity currentRole = new UserRoleEntity();
        currentRole.setRole(UserRolesEnums.USER);
        user.getRoles().add(currentRole);


        String newRole = "ADMIN";
        UserRoleEntity newRoleEntity = new UserRoleEntity();
        newRoleEntity.setRole(UserRolesEnums.valueOf(newRole));


        when(mockUserRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        when(mockUserRoleRepository.findByRole(UserRolesEnums.valueOf(newRole))).thenReturn(Optional.of(newRoleEntity));


        assertDoesNotThrow(() -> userService.changeUserRole(user.getUsername(), newRole));

        assertEquals(1, user.getRoles().size());
        assertTrue(user.getRoles().contains(newRoleEntity));
    }


    @Test
    void changeUserRoleShouldHandleUserNotFound() {


        String newRole = "ADMIN";


        when(mockUserRepository.findByUsername("nonexistentUser")).thenReturn(Optional.empty());


        assertThrows(RuntimeException.class, () -> userService.changeUserRole("nonexistentUser", newRole));
    }




}
