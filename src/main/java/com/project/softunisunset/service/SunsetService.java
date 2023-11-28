package com.project.softunisunset.service;

import com.project.softunisunset.models.dto.CreateSunsetDTO;
import com.project.softunisunset.models.entity.Continent;
import com.project.softunisunset.models.entity.Sunset;
import com.project.softunisunset.models.entity.User;
import com.project.softunisunset.models.enums.ContinentName;
import com.project.softunisunset.repositories.ContinentRepository;
import com.project.softunisunset.repositories.SunsetRepository;
import com.project.softunisunset.repositories.UserRepository;
import com.project.softunisunset.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SunsetService {
    private final UserRepository userRepository;
    private final SunsetRepository sunsetRepository;
    private final ContinentRepository continentRepository;
    private final ModelMapper modelMapper;
    private LoggedUser loggedUser;

    public SunsetService(UserRepository userRepository, SunsetRepository sunsetRepository, ContinentRepository continentRepository, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.sunsetRepository = sunsetRepository;
        this.continentRepository = continentRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    public boolean createSunset(CreateSunsetDTO createSunsetDTO){

        ContinentName continentName = ContinentName.valueOf(createSunsetDTO.getContinent());

        Optional<User> currentUser = userRepository.findById(loggedUser.getId());

        Optional<Continent> continent = this.continentRepository.findByName(continentName);



        if(continent.isEmpty()|| currentUser.isEmpty()){
            return false;
        }

        this.sunsetRepository.save(this.modelMapper.map(createSunsetDTO, Sunset.class));



        return true;
    }


    public List<Sunset> getAllSunsets(){
        return this.sunsetRepository.findAll();
    }
}
