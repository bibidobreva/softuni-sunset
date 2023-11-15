package com.project.softunisunset.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Table
@Entity
public class Sunset extends BaseEntity{


    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private LocalDate date;

    @Lob
    private byte[] photo;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @ManyToOne
    private Continent continent;


    private Sunset(){}

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }
}
