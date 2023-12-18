package com.project.softunisunset.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerTestIT {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testRegistration() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/register")
                        .param("username", "usernameTest")
                        .param("firstName", "fistNameTest")
                        .param("lastName", "lastNameTest")
                        .param("email", "email@example.com")
                        .param("password", "123456")
                        .param("confirmPassword", "123456")
                        .with(csrf())
        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/register"));
    }
}