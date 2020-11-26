package by.mybrik.controllers;

import by.mybrik.domain.Users;
import by.mybrik.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    // http://localhost:8080/rest/users/18
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Users findUserById(@PathVariable Long id){
        return usersService.findById(id);
    }

    // http://localhost:8080/rest/users/18
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Users> deleteUserById(@PathVariable("id") Long id){
        Users deleteUser = usersService.findById(id);
        usersService.delete(deleteUser);
        return usersService.findAll();
    }

}
