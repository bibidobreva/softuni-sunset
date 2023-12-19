package com.project.softunisunset.controller;

import com.project.softunisunset.models.dto.ChangeUsernameDTO;
import com.project.softunisunset.models.entity.User;
import com.project.softunisunset.service.SunsetService;
import com.project.softunisunset.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserProfileControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private SunsetService sunsetService;

    @Test
    void testGetChangeUsernamePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/username").with(SecurityMockMvcRequestPostProcessors.user("testUser")))
                .andExpect(status().isOk())
                .andExpect(view().name("username"));
    }

    @Test
    void testGetLikesPage() throws Exception {
        User user = new User();
        user.setUsername("testUser");

        when(userService.getCurrentUser()).thenReturn(user);
        when(userService.getAllLikedSunsetsOfUser(user)).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/profile/likes").with(SecurityMockMvcRequestPostProcessors.user("testUser")))
                .andExpect(status().isOk())
                .andExpect(view().name("liked-sunsets"))
                .andExpect(model().attributeExists("sunsets"));
    }


}
