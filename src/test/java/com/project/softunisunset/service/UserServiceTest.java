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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private SunsetRepository mockSunsetRepository;
    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private PasswordEncoder mockPasswordEncoder;
    @Mock
    private UserRoleRepository mockUserRoleRepository;
    @Mock
    private UserDetailsService mockUserDetailsService;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        mockUserRepository = mock(UserRepository.class);
        mockSunsetRepository = mock(SunsetRepository.class);
        mockModelMapper = mock(ModelMapper.class);
        mockPasswordEncoder = mock(PasswordEncoder.class);
        mockUserRoleRepository = mock(UserRoleRepository.class);
        mockUserDetailsService = mock(UserDetailsService.class);
        userService = new UserService(mockUserRepository, mockSunsetRepository, mockModelMapper, mockPasswordEncoder, mockUserRoleRepository, mockUserDetailsService);
    }

    @Test
    void registerShouldReturnTrueWhenRegistrationIsSuccessful() {

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


        boolean result = userService.register(registrationDTO);


        assertTrue(result);
        verify(mockUserRepository, times(1)).save(any());
    }


    @Test
    void getAllUsersShouldReturnAllUsers() {

        User user1 = new User();
        User user2 = new User();
        when(mockUserRepository.findAll()).thenReturn(Arrays.asList(user1, user2));


        List<User> result = userService.getAllUsers();


        assertEquals(2, result.size());
        assertTrue(result.contains(user1));
        assertTrue(result.contains(user2));
        verify(mockUserRepository, times(1)).findAll();
    }


    @Test
    void getCurrentUserShouldReturnCurrentUser() {

        String loggedInUsername = "testUser";
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(loggedInUsername);

        SecurityContextHolder.setContext(securityContext);

        User expectedUser = new User();
        when(mockUserRepository.findByUsername(loggedInUsername)).thenReturn(Optional.of(expectedUser));



        User result = userService.getCurrentUser();


        assertEquals(expectedUser, result);
        verify(mockUserRepository, times(1)).findByUsername(loggedInUsername);
    }


    @Test
    void getCurrentUserShouldThrowRuntimeExceptionWhenUserNotFound() {

        String loggedInUsername = "nonexistentUser";
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);

        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(loggedInUsername);

        when(mockUserRepository.findByUsername(loggedInUsername)).thenReturn(Optional.empty());


        assertThrows(RuntimeException.class, () -> userService.getCurrentUser());
        verify(mockUserRepository, times(1)).findByUsername(loggedInUsername);
    }


    @Test
    void likeSunsetShouldAddSunsetToLikedSunsets() {

        User user = new User();
        Long sunsetId = 123L;
        Sunset sunset = new Sunset();

        when(mockSunsetRepository.findById(sunsetId)).thenReturn(Optional.of(sunset));


        userService.likeSunset(user, sunsetId);


        assertTrue(user.getLikedSunsets().contains(sunset));
        verify(mockUserRepository, times(1)).save(user);
    }

    @Test
    void likeSunsetShouldThrowRuntimeExceptionWhenSunsetNotFound() {

        User user = new User();
        Long sunsetId = 123L;

        when(mockSunsetRepository.findById(sunsetId)).thenReturn(Optional.empty());


        assertThrows(RuntimeException.class, () -> userService.likeSunset(user, sunsetId));
        verify(mockUserRepository, never()).save(any(User.class));
    }


    @Test
    void getAllLikedSunsetsOfUserShouldReturnLikedSunsets() {



        User user = new User();
        List<Sunset> likedSunsets = new ArrayList<>();
        likedSunsets.add(new Sunset());
        likedSunsets.add(new Sunset());
        user.setLikedSunsets(likedSunsets);


        List<Sunset> result = userService.getAllLikedSunsetsOfUser(user);


        assertEquals(likedSunsets, result);
    }


    @Test
    void changeUsernameShouldChangeUsernameSuccessfully() {

        User user = new User();
        user.setPassword("test");
        user.setUsername("oldUsername");

        ChangeUsernameDTO changeUsernameDTO = new ChangeUsernameDTO();
        changeUsernameDTO.setUsername("newUsername");


        when(mockUserRepository.findByUsername(any())).thenReturn(Optional.empty());
        when(mockUserRepository.save(user)).thenReturn(user);

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                "test",
                Collections.emptyList());
        when(mockUserDetailsService.loadUserByUsername(any())).thenReturn(userDetails);


        boolean result = userService.changeUsername(changeUsernameDTO, user);


        assertTrue(result);
        assertEquals(changeUsernameDTO.getUsername(), user.getUsername());
    }





    @Test
    void changeUsernameShouldFailDueToExistingUsername() {


        User user = new User();
        user.setUsername("oldUsername");


        ChangeUsernameDTO changeUsernameDTO = new ChangeUsernameDTO();
        changeUsernameDTO.setUsername("existingUsername");


        when(mockUserRepository.findByUsername(changeUsernameDTO.getUsername()))
                .thenReturn(Optional.of(new User()));



        boolean result = userService.changeUsername(changeUsernameDTO, user);


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
