package com.project.softunisunset.models.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class CreateEventDTO {
    @Size(min = 2, max = 20)
    @NotBlank
    private String name;
    @Size(min = 2, max = 200)
    @NotBlank
    private String description;
    @Future
    @NotNull
    private LocalDate date;

    public CreateEventDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
