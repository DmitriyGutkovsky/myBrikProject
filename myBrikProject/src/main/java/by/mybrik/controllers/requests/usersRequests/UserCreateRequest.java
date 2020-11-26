package by.mybrik.controllers.requests.usersRequests;

import by.mybrik.domain.Gender;
import lombok.Data;

@Data
public class UserCreateRequest {

    private String name;

    private String surName;

    private String login;

    private String password;

    private String email;

    private Gender gender;

    private int phone;

    private String address;

    private boolean isDeleted;

}
