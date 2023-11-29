package com.project.softunisunset.models.dto;

import java.time.LocalDate;

public class CreateEventDTO {
    private String name;
    private String description;
    private LocalDate date;

    public CreateEventDTO(){}

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
