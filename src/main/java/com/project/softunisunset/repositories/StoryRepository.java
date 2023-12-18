package com.project.softunisunset.repositories;

import com.project.softunisunset.models.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
    List<Story> findAllByOrderByIdDesc();
}
