package by.mybrik.controllers.requests.usersRequests;

import by.mybrik.domain.Gender;
import lombok.Data;

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

}
