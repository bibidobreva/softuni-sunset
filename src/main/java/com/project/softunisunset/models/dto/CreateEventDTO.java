package com.project.softunisunset.models.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class CreateEventDTO {
    @Size(min = 2, max = 20)
    private String name;
    @Size(min = 2, max = 200)
    private String description;
    @Future
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
