package com.project.softunisunset.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ChangeUsernameDTO {

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    public ChangeUsernameDTO(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
