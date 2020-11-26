package by.mybrik.controllers;

import by.mybrik.domain.Users;
import by.mybrik.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/users")
@RequiredArgsConstructor
public class UsersRestController {

    public final UsersService usersService;

    // http://localhost:8080/rest/users
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Users> getAllUsers(){
        return usersService.findAll();
    }

}
