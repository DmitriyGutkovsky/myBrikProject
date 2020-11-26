package by.mybrik.controllers;

import by.mybrik.controllers.requests.usersRequests.UserCreateRequest;
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
    /*
    http://localhost:8080/rest/users
    {
    "name": "saveUser",
    "surName": "saveUser",
    "login": "saveUser",
    "password": "save809",
    "email": "sav809User@mail.ru",
    "gender": "FEMALE",
    "phone": 35698980,
    "address": "sav8099",
    "deleted": false
    }
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Users registerUser (@RequestBody UserCreateRequest request){

        Users user = new Users();

        user.setName(request.getName());
        user.setSurName(request.getSurName());
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setGender(request.getGender());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setDeleted(request.isDeleted());

        return usersService.save(user);
    }

}
