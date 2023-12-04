package com.project.softunisunset.service;

import com.project.softunisunset.models.dto.CreateSunsetDTO;
import com.project.softunisunset.models.entity.Continent;
import com.project.softunisunset.models.entity.Sunset;
import com.project.softunisunset.models.entity.User;
import com.project.softunisunset.models.enums.ContinentName;
import com.project.softunisunset.repositories.ContinentRepository;
import com.project.softunisunset.repositories.SunsetRepository;
import com.project.softunisunset.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SunsetService {
    private final UserRepository userRepository;
    private final SunsetRepository sunsetRepository;
    private final ContinentRepository continentRepository;
    private final ModelMapper modelMapper;


    public SunsetService(UserRepository userRepository, SunsetRepository sunsetRepository, ContinentRepository continentRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.sunsetRepository = sunsetRepository;
        this.continentRepository = continentRepository;
        this.modelMapper = modelMapper;

    }

    public boolean createSunset(CreateSunsetDTO createSunsetDTO){

        ContinentName continentName = ContinentName.valueOf(createSunsetDTO.getContinent());

        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> optionalUser = this.userRepository.findByUsername(loggedInUsername);

        Optional<Continent> continent = this.continentRepository.findByName(continentName);



        if(continent.isEmpty()|| optionalUser.isEmpty()){
            return false;
        }


        Sunset sunset = this.modelMapper.map(createSunsetDTO, Sunset.class);

        sunset.setUser(optionalUser.get());
        sunset.setContinent(continent.get());

        this.sunsetRepository.save(sunset);



        return true;
    }



    public List<Sunset> getAllSunsetsByContinent(ContinentName continentName) {
        Optional<Continent> continent = this.continentRepository.findByName(continentName);

        if (continent.isEmpty()) {

            return Collections.emptyList();
        }

        return this.sunsetRepository.findAllByContinent(continent.get());
    }


    public List<Sunset> getAllSunsets(){
        return this.sunsetRepository.findAll();
    }
}
