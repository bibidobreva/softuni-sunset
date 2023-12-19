package com.project.softunisunset.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTestIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void testLoginErrorPage() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/login-error")
                                .param("username", "testUsername")
                                .with(csrf())
                ).andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("username", "bad_credentials"));
    }
}
