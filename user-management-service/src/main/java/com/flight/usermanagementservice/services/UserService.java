package com.flight.usermanagementservice.services;

import java.util.HashSet;
import java.util.Set;

import com.flight.usermanagementservice.model.Role;
import com.flight.usermanagementservice.model.User;
import com.flight.usermanagementservice.repository.RoleRepo;
import com.flight.usermanagementservice.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User setNewUser(User user) {
        return userRepo.save(user);
    }

    public User registerNewUser(User user) {

        Role role = roleRepo.findById("USER").get();
        System.out.println("Roles : " + role);

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return user;
    }

    public void initRolesAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin Role");
        roleRepo.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Passenger Role");
        roleRepo.save(userRole);

        Role airportAuthority = new Role();
        airportAuthority.setRoleName("AIRPORT AUTHORITY");
        airportAuthority.setRoleDescription("Airport Authority Role");
        roleRepo.save(airportAuthority);

        User user = new User();
        user.setUsername("Hritik");
        user.setPassword(passwordEncoder.encode("Hritik@13"));
        user.setEmailId("hritikr13@gmail.com");
        user.setFirstName("Hritik");
        user.setLastName("Ray");
        user.setMobile(977721632);
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        user.setRoles(adminRoles);
        userRepo.save(user);

        User authority = new User();
        authority.setUsername("Ruchika");
        authority.setPassword(passwordEncoder.encode("Ruchika@123"));
        authority.setEmailId("ruchika@gmail.com");
        authority.setFirstName("Ruchika");
        authority.setLastName("Ray");
        authority.setMobile(800900900);
        Set<Role> airportAuthorityRoles = new HashSet<>();
        airportAuthorityRoles.add(airportAuthority);
        authority.setRoles(airportAuthorityRoles);
        userRepo.save(authority);
    }

}

