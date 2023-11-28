package com.project.softunisunset.repositories;

import com.project.softunisunset.models.entity.Continent;
import com.project.softunisunset.models.enums.ContinentName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContinentRepository extends JpaRepository<Continent, Long> {
    Optional<Continent> findByName(ContinentName continentName);
}
