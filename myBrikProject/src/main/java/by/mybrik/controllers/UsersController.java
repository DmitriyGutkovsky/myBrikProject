package by.mybrik.controllers;

import by.mybrik.controllers.requests.usersRequests.UserCreate;
import by.mybrik.controllers.requests.usersRequests.UsersUpdate;
import by.mybrik.domain.entities.Users;
import by.mybrik.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/new/rest/users")
public class UsersController {

  public final UsersService usersService;

  // http://localhost:8080/new/rest/users
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Users> getAllUsers() {
    return usersService.findAll();
  }

  // http://localhost:8080/new/rest/users/20
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Users findUserById(@PathVariable Long id){
    return usersService.findById(id);
  }

  /*
    http://localhost:8080/new/rest/users
    {
    "name": "saveUserTest",
    "surName": "saveUserTest",
    "login": "saveUserTest",
    "password": "save809",
    "email": "saveUserTest@mail.ru",
    "gender": "FEMALE",
    "phone": 356938980,
    "address": "sav8099",
    "deleted": false
    }
     */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Users registerUser (@RequestBody UserCreate request){

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

  /*
   http://localhost:8080/new/rest/users/21
    {
    "name": "saveUserTestUpdate",
    "surName": "saveUserTest",
    "login": "saveUserTest",
    "password": "save809",
    "email": "saveUserTest@mail.ru",
    "gender": "FEMALE",
    "phone": 356938980,
    "address": "sav8099",
    "deleted": false
    }
    */
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Users updateUserDetails(@PathVariable Long id, @RequestBody UsersUpdate request){

    Users user = usersService.findById(id);

    user.setName(request.getName());
    user.setSurName(request.getSurName());
    user.setLogin(request.getLogin());
    user.setPassword(request.getPassword());
    user.setEmail(request.getEmail());
    user.setGender(request.getGender());
    user.setPhone(request.getPhone());
    user.setAddress(request.getAddress());
    user.setDeleted(request.isDeleted());
    user.setChanged(new Timestamp(System.currentTimeMillis()));

    return usersService.update(user);
  }

  // http://localhost:8080/new/rest/users/21
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public List<Users> deleteUserById(@PathVariable("id") Long id){
    Users deleteUser = usersService.findById(id);
    usersService.delete(deleteUser);
    return usersService.findAll();
  }
}
