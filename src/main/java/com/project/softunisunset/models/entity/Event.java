package com.project.softunisunset.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Table
@Entity
public class Event extends BaseEntity{
    @Column
    @Size(min = 2, max = 20)
    private String name;
    @Column(columnDefinition = "text")
    @Size(min = 2, max = 200)
    private String description;

    @Future
    private LocalDate date;


    public Event(){}

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
