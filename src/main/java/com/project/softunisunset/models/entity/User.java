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
}
