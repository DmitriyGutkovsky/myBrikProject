package by.mybrik.controllers;

import by.mybrik.controllers.requests.roleRequests.RoleUpdate;
import by.mybrik.controllers.requests.usersRequests.UserCreate;
import by.mybrik.controllers.requests.usersRequests.UsersUpdate;
import by.mybrik.domain.Role;
import by.mybrik.domain.SystemRoles;
import by.mybrik.domain.Users;
import by.mybrik.repository.impl.UsersRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/new/rest/users")
public class UsersController {

  public final UsersRepository usersRepository;

  private final PasswordEncoder passwordEncoder;

  // http://localhost:8080/new/rest/users
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Users> getAllUsers() {
    return usersRepository.findAll();
  }

  // http://localhost:8080/new/rest/users/20
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Users> findUserById(@PathVariable Long id) {
    return usersRepository.findById(id);
  }

  // http://localhost:8080/new/rest/users/21
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public List<Users> deleteUserById(@PathVariable("id") Long id) {
    if (!usersRepository.existsById(id)) {
      // TODO make own Exception
      throw new RuntimeException();
    }
    usersRepository.deleteById(id);
    return usersRepository.findAll();
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
  @ApiOperation(value = "Endpoint for creation users")
  @ApiImplicitParams(
          @ApiImplicitParam(name = "Auth-Token", defaultValue = "token",
                  required = true, paramType = "header", dataType = "String")
  )
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Users createNewUser(@RequestBody UserCreate request) {

    Users user = new Users();

    user.setName(request.getName());
    user.setSurName(request.getSurName());
    user.setLogin(request.getLogin());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setEmail(request.getEmail());
    user.setGender(request.getGender());
    user.setPhone(request.getPhone());
    user.setAddress(request.getAddress());
    user.setDeleted(request.isDeleted());

    user.setRoles(Collections.singleton(new Role(SystemRoles.ROLE_USER, user)));

    return usersRepository.save(user);
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
  public Users updateUserDetails(@PathVariable Long id, @RequestBody UsersUpdate request) {
//  public Users updateUserDetails(@PathVariable Long id, @RequestBody UsersUpdate request, @ModelAttribute RoleUpdate roleUpdate) {

    if (!usersRepository.existsById(id)) {
      // TODO own Exception
      throw new RuntimeException();
    }

    Users user = usersRepository.getOne(id);

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
//    user.setRoles(request.getRole());
//    user.setRoles(Collections.singleton(new Role(roleUpdate.getSystemRoles(), user)));

    return usersRepository.save(user);
  }

  @GetMapping("/findbylogin")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Users> findUserByLogin(@RequestParam(name = "login") String login) {
    return usersRepository.findByLogin(login);
  }

  @GetMapping("/roles/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public Set<Role> findUserRoles(@PathVariable(name = "userId") Long userId) {
    Set<Role> roles = usersRepository.getOne(userId).getRoles();
    return roles;
  }
}
