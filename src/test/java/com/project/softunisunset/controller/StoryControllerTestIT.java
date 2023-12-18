package com.project.softunisunset.controller;


import com.project.softunisunset.models.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StoryControllerTestIT {
    @Autowired
    private MockMvc mockMvc;





    @Test
    void testPostStoriesAddSuccess() throws Exception {
        User user = new User();
        user.setUsername("test");
        mockMvc.perform(MockMvcRequestBuilders.post("/blog/add")
                        .param("name", "Test Story")
                        .param("story", "This is a test story.")
                        .with(csrf())
                        .with(user("test"))) // Provide the username, password, and roles as needed
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/blog"));
    }

    @Test
    void testGetStories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/blog").with(user("test")))
                .andExpect(status().isOk())
                .andExpect(view().name("blog"))
                .andExpect(model().attributeExists("storyList"));
    }


}
