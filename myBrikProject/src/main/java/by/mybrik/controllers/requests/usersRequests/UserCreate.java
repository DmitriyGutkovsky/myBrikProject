package by.mybrik.controllers.requests.usersRequests;

import by.mybrik.domain.Gender;
import by.mybrik.domain.Role;
import by.mybrik.domain.SystemRoles;
import lombok.Data;

import java.util.Collections;
import java.util.Set;

@Data
public class UserCreate {

    private String name;

    private String surName;

    private String login;

    private String password;

    private String email;

    private Gender gender;

    private String phone;

    private String address;

    private boolean isDeleted;

    private Set<Role> role = Collections.singleton(new Role(SystemRoles.ROLE_USER));

}
