package com.project.softunisunset.models.entity;

import com.project.softunisunset.models.enums.UserRolesEnums;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserRoleEntity extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private UserRolesEnums role;

    public UserRoleEntity(){}

    public UserRolesEnums getRole() {
        return role;
    }

    public void setRole(UserRolesEnums role) {
        this.role = role;
    }
}
