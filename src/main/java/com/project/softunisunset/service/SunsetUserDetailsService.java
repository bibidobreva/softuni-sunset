package com.project.softunisunset.service;

import com.project.softunisunset.models.entity.User;
import com.project.softunisunset.models.entity.UserRoleEntity;
import com.project.softunisunset.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public class SunsetUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;


    public SunsetUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(SunsetUserDetailsService::map)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found!"));
    }

    private static UserDetails map(User user) {
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles().stream().map(SunsetUserDetailsService::map).toList())
                .build();

    }

    private static GrantedAuthority map(UserRoleEntity userRoleEntity) {

        return new SimpleGrantedAuthority("ROLE_" + userRoleEntity.getRole().name());
    }


}
