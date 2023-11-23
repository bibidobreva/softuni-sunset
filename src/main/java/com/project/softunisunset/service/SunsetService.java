package com.project.softunisunset.service;

import com.project.softunisunset.models.dto.CreateSunsetDTO;
import com.project.softunisunset.models.entity.Sunset;
import com.project.softunisunset.repositories.SunsetRepository;
import com.project.softunisunset.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SunsetService {
    private final UserRepository userRepository;
    private final SunsetRepository sunsetRepository;
    private final ModelMapper modelMapper;

    public SunsetService(UserRepository userRepository, SunsetRepository sunsetRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.sunsetRepository = sunsetRepository;
        this.modelMapper = modelMapper;
    }

    public boolean createSunset(CreateSunsetDTO createSunsetDTO){

        //check user
        //check continent

        this.sunsetRepository.save(this.modelMapper.map(createSunsetDTO, Sunset.class));



        return true;
    }
}
