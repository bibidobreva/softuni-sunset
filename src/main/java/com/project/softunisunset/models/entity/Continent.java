package com.project.softunisunset.models.entity;

import com.project.softunisunset.models.enums.ContinentName;
import jakarta.persistence.*;

import java.util.List;

@Table
@Entity
public class Continent extends BaseEntity{
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContinentName name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "sunset")
    private List<Sunset> sunsets;


    public Continent() {
    }


    public ContinentName getName() {
        return name;
    }

    public void setName(ContinentName name) {
        this.name = name;
        setContinentDesc(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Sunset> getSunsets() {
        return sunsets;
    }

    public void setSunsets(List<Sunset> sunsets) {
        this.sunsets = sunsets;
    }

    public void setContinentDesc(ContinentName continentName){
        String desc = "";
        switch (continentName){
            case ASIA : desc = "Where the sun rises";
            break;
            case AFRICA : desc = "The jungle";
            break;
            case EUROPE:desc = "The old continent";
            break;
            case ANTARCTICA:desc = "The cold place";
            case NORTH_AMERICA: desc = "The new land";
            break;
            case SOUTH_AMERICA:desc = "The latin lands";
            break;
            case AUSTRALIA: desc = "Kangoroo land";
        }
        this.description = desc;
    }
}
