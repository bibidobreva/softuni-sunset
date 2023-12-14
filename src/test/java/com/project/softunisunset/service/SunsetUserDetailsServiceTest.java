package com.project.softunisunset.service;

import com.project.softunisunset.models.entity.User;
import com.project.softunisunset.models.entity.UserRoleEntity;
import com.project.softunisunset.models.enums.UserRolesEnums;
import com.project.softunisunset.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SunsetUserDetailsServiceTest {
    private SunsetUserDetailsService serviceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp(){
        serviceToTest = new SunsetUserDetailsService(mockUserRepository);
    }

    @Test
    void testMock(){

        User user = new User();
        user.setFirstName("Anna");
        when(mockUserRepository.findByUsername("example"))
                .thenReturn(Optional.of(user));

        Optional<User> userOpt = mockUserRepository.findByUsername("example");

        User userOption = userOpt.get();
        Assertions.assertEquals("Anna",userOption.getFirstName() );
    }

    @Test
    void userNotFound(){
        Assertions.assertThrows(UsernameNotFoundException.class,
                ()->serviceToTest.loadUserByUsername("test"));

    }

    @Test
    void  userFoundException(){

        User testUser = createUser();
        when(mockUserRepository.findByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));

        UserDetails userDetails = serviceToTest.loadUserByUsername(testUser.getUsername());

        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals(testUser.getUsername(),userDetails.getUsername());
        Assertions.assertEquals(testUser.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(1, userDetails.getAuthorities().size());

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
