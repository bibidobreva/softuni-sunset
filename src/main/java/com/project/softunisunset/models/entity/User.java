package com.project.softunisunset.models.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Table
@Entity
public class User extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(nullable = false)
    private LocalDate birthdate;

    @OneToMany(mappedBy = "user")
    private List<Sunset> sunsets;

    @ManyToMany
    private List<Sunset> likedSunsets;

    @OneToMany(mappedBy = "user")
    private List<Story> stories;




    public User(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public List<Sunset> getSunsets() {
        return sunsets;
    }

    public void setSunsets(List<Sunset> sunsets) {
        this.sunsets = sunsets;
    }

    public List<Sunset> getLikedSunsets() {
        return likedSunsets;
    }

    public void setLikedSunsets(List<Sunset> likedSunsets) {
        this.likedSunsets = likedSunsets;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }
}
