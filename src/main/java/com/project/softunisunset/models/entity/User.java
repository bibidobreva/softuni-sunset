package com.project.softunisunset.models.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
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



    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Sunset> sunsets;

    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.PERSIST)
    private List<Sunset> likedSunsets;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Story> stories;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<UserRoleEntity> roles = new ArrayList<>();


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

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
    }
}
