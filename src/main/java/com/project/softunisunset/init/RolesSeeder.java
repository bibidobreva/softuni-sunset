package com.project.softunisunset.init;

import com.project.softunisunset.models.entity.Continent;
import com.project.softunisunset.models.entity.UserRoleEntity;
import com.project.softunisunset.models.enums.ContinentName;
import com.project.softunisunset.models.enums.UserRolesEnums;
import com.project.softunisunset.repositories.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RolesSeeder implements CommandLineRunner {
   private final UserRoleRepository userRoleRepository;

    public RolesSeeder(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(this.userRoleRepository.count()<=0){
            List<UserRoleEntity> userRoleEntityList = new ArrayList<>();

            Arrays.stream(UserRolesEnums.values())
                    .forEach(userRole->{
                        UserRoleEntity userRoleEntity = new UserRoleEntity();
                        userRoleEntity.setRole(userRole);
                        userRoleEntityList.add(userRoleEntity);
                    });



            this.userRoleRepository.saveAll(userRoleEntityList);

        }
    }
}
