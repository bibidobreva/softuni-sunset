package com.project.softunisunset.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class InfoPageControllerTestIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetContacts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/contacts"))
                .andExpect(status().isOk())
                .andExpect(view().name("contact"));
    }

    @Test
    void testGetAbout() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/about"))
                .andExpect(status().isOk())
                .andExpect(view().name("about"));
    }
}
