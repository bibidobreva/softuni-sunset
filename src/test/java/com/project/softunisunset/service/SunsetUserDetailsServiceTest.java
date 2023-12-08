package com.project.softunisunset.service;

import com.project.softunisunset.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    }
}
