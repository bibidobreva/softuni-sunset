package com.project.softunisunset.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Base64;

@Table
@Entity
public class Sunset extends BaseEntity{


    @Column(nullable = false)
    @Size(min = 2, max = 20)
    private String location;

    @Column(nullable = false)
    @PastOrPresent
    private LocalDate date;

    @Lob
    @Column(columnDefinition = "BLOB")
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
    public String getBase64Photo() {
        return Base64.getEncoder().encodeToString(photo);
    }
}
