package com.project.softunisunset.models.dto;

import com.project.softunisunset.models.entity.User;

import java.time.LocalDate;

public class CreateSunsetDTO {
    private String location;
    private LocalDate date;
    private byte[] photo;
    private String continent;

    private User user;

    public CreateSunsetDTO(){}
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
