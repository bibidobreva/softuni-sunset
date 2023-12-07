package com.project.softunisunset.repositories;

import com.project.softunisunset.models.entity.Continent;
import com.project.softunisunset.models.entity.Sunset;
import com.project.softunisunset.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SunsetRepository extends JpaRepository<Sunset, Long> {
    List<Sunset> findAllByContinent(Continent continent);

    List<Sunset> findAllByUser(User user);
}
