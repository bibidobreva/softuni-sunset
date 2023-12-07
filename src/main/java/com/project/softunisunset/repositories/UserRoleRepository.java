package com.project.softunisunset.repositories;

import com.project.softunisunset.models.entity.UserRoleEntity;
import com.project.softunisunset.models.enums.UserRolesEnums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {


    Optional<UserRoleEntity> findByRole(UserRolesEnums userRolesEnums);
}
