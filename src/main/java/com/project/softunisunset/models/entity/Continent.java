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

    @Column(nullable = false, columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "continent")
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
            case ASIA : desc = "Earth's largest continent, Asia spans from the Arctic Ocean to the Indian Ocean. It hosts diverse cultures, from the ancient civilizations of China and India to the modern metropolises of Tokyo and Dubai. The Himalayas and the Great Wall are among its prominent features.";
            break;
            case AFRICA : desc = "Known for its diverse landscapes, Africa is the second-largest continent, home to the Sahara Desert and iconic wildlife like lions and elephants. Rich in culture and history, it boasts the Nile River and the vibrant cities of Cairo and Johannesburg.";
            break;
            case EUROPE:desc = "Renowned for its historical significance, Europe is a continent of diverse cultures, languages, and landscapes. From the romantic allure of Paris to the ancient ruins of Rome, it has played a pivotal role in shaping global politics, art, and philosophy.";
            break;
            case ANTARCTICA:desc = "The southernmost continent, Antarctica is a vast, icy wilderness. It's known for its extreme cold, unique wildlife like penguins and seals, and scientific research stations. With no permanent population, it's a haven for international scientific cooperation.";
            break;
            case NORTH_AMERICA: desc = "Home to the United States and Canada, North America features vast landscapes, from the Rocky Mountains to the Great Plains. Iconic landmarks like the Grand Canyon and the Statue of Liberty are found here, along with a rich tapestry of cultures.";
            break;
            case SOUTH_AMERICA:desc = "Known for the Amazon Rainforest and the Andes Mountains, South America is a continent of ecological and cultural richness. Vibrant cities like Rio de Janeiro and Buenos Aires coexist with ancient ruins, such as Machu Picchu in Peru.";
            break;
            case AUSTRALIA: desc = "Comprising Australia, New Zealand, and the Pacific Islands, Oceania is a region surrounded by the Pacific Ocean. It offers stunning natural wonders like the Great Barrier Reef and cultural diversity among the indigenous peoples.";
            break;
        }
        this.description = desc;
    }
}
