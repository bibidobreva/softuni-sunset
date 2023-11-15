package com.project.softunisunset.repositories;

import com.project.softunisunset.models.entity.Sunset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SunsetRepository extends JpaRepository<Sunset, Long> {
}
