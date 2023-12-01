package com.project.softunisunset.init;

import com.project.softunisunset.models.entity.Continent;
import com.project.softunisunset.models.enums.ContinentName;
import com.project.softunisunset.repositories.ContinentRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ContinentSeeder implements CommandLineRunner {

    public final ContinentRepository continentRepository;

    public ContinentSeeder(ContinentRepository continentRepository) {
        this.continentRepository = continentRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        if (this.continentRepository.count()<=0){
            List<Continent> continents = new ArrayList<>();

            Arrays.stream(ContinentName.values())
                    .forEach(continentName -> {
                        Continent continent = new Continent();
                        continent.setName(continentName);
                        continents.add(continent);
                    });




            this.continentRepository.saveAll(continents);
        }
    }
}
