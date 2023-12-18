package com.project.softunisunset.controller;

import com.project.softunisunset.models.entity.User;
import com.project.softunisunset.models.entity.UserRoleEntity;
import com.project.softunisunset.models.enums.UserRolesEnums;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTestIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetEventsPage() throws Exception {
        User user = new User();
        user.setUsername("test");

        mockMvc.perform(MockMvcRequestBuilders.get("/events").with(user("test")))
                .andExpect(status().isOk())
                .andExpect(view().name("events"))
                .andExpect(model().attributeExists("eventList"));
    }

    @Test
    void testGetAddEventPage() throws Exception {
        createAdminUser();
        mockMvc.perform(MockMvcRequestBuilders.get("/events/add").with(user("test").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("add-event"));
    }


    @Test
    void testPostAddEventSuccess() throws Exception {
        createAdminUser();
        mockMvc.perform(MockMvcRequestBuilders.post("/events/add")
                        .param("eventName", "Test Event")
                        .param("eventDate", "2023-12-31")
                        .param("location", "Test Location")
                        .param("description", "Test Description")
                        .with(csrf())
                        .with(user("test").roles("ADMIN")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/events"));
    }



    private static void createAdminUser() {
        User user = new User();
        user.setUsername("test");
        List<UserRoleEntity> roles = new ArrayList<>();
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRole(UserRolesEnums.ADMIN);
        roles.add(userRoleEntity);
        user.setRoles(roles);
    }

}
