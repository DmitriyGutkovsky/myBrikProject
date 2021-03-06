package by.mybrik.controllers;

import by.mybrik.controllers.requests.usersRequests.UserCreate;
import by.mybrik.controllers.requests.usersRequests.UsersUpdate;
import by.mybrik.domain.Role;
import by.mybrik.domain.SystemRoles;
import by.mybrik.domain.Users;
import by.mybrik.exceptions.EntityNotFoundException;
import by.mybrik.repository.impl.RoleRepository;
import by.mybrik.repository.impl.UsersRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/new/rest/users")
public class UsersController {

  public final UsersRepository usersRepository;

  public final ConversionService conversionService;

  @ApiOperation(value = "Endpoint for getting a list of all users")
  @Secured("ROLE_ADMIN")
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Users> findAllUsers() {
    return usersRepository.findAll();
  }

  @ApiOperation(value = "Endpoint for getting a specified user by id")
  @Secured({"ROLE_ADMIN", "ROLE_USER"})
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Users> findUserById(@PathVariable Long id) {
    if (!usersRepository.existsById(id)) {
      throw new EntityNotFoundException(String.format("There is no user with id = %d", id));
    }
    return usersRepository.findById(id);
  }

  @ApiOperation(value = "Endpoint for deleting from Database a specified user by id")
  @Secured("ROLE_ADMIN")
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public List<Users> deleteUserById(@PathVariable("id") Long id) {
    if (!usersRepository.existsById(id)) {
      throw new EntityNotFoundException(String.format("There is no user with id = %d", id));
    }
    usersRepository.deleteById(id);
    return usersRepository.findAll();
  }

  @ApiOperation(value = "Endpoint for user creation")
  @Secured("ROLE_ADMIN")
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Users createNewUser(@RequestBody UserCreate request) {

    Users user = conversionService.convert(request, Users.class);

    return usersRepository.save(user);
  }

  @ApiOperation(value = "Endpoint for updating user details")
  @Secured({"ROLE_ADMIN", "ROLE_USER"})
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Users updateUserDetails(@PathVariable Long id, @RequestBody UsersUpdate request) {

    request.setId(id);
    Users user = conversionService.convert(request, Users.class);

    return usersRepository.save(user);
  }

  @ApiOperation(value = "Endpoint for finding a user by login")
  @Secured({"ROLE_ADMIN", "ROLE_USER"})
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @GetMapping("/findbylogin")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Users> findUserByLogin(@RequestParam(name = "login") String login) {
    return usersRepository.findByLogin(login);
  }

  @ApiOperation(value = "Endpoint for getting user roles by user id")
  @Secured("ROLE_ADMIN")
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @GetMapping("/roles/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public Set<Role> findUserRoles(@PathVariable(name = "userId") Long userId) {
    Set<Role> roles = usersRepository.getOne(userId).getRoles();
    return roles;
  }

  @ApiOperation(
      value =
          "Endpoint for changing user status: "
              + "if account is active isDeleted should be put on false, "
              + "if account is not active isDeleted should be put on true")
  @Secured("ROLE_ADMIN")
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @GetMapping("/change_status")
  @ResponseStatus(HttpStatus.OK)
  public Users changeStatus(
      @RequestParam(name = "login") String login, @RequestParam(name = "status") Boolean status) {

    if (!usersRepository.existsByLogin(login)) {
      throw new EntityNotFoundException("There is no user with login = " + login);
    }

    Users user = usersRepository.findByLogin(login).get();

    user.setDeleted(status);
    user.setChanged(new Timestamp(System.currentTimeMillis()));

    return usersRepository.save(user);
  }

  public final RoleRepository roleRepository;

  @ApiOperation(value = "Endpoint for granting to user new role")
  @Secured("ROLE_ADMIN")
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @PutMapping("/adding_role_to_user")
  @ResponseStatus(HttpStatus.OK)
  public Role addRole(@RequestParam Long userId, @RequestParam SystemRoles grantedRole) {

    if (!usersRepository.existsById(userId)) {
      throw new EntityNotFoundException(String.format("There is no user with id = %d", userId));
    }

    Optional<Users> user = usersRepository.findById(userId);

    List<SystemRoles> collectedRoles =
        user.get().getRoles().stream().map(Role::getRoleName).collect(Collectors.toList());

    if (collectedRoles.contains(grantedRole)) {
      throw new EntityNotFoundException("User already has such role");
    }

    Role role = new Role();
    role.setRoleName(grantedRole);
    role.setUser(user.get());

    return roleRepository.save(role);
  }

  @ApiOperation(value = "Endpoint for deleting a role from user")
  @Secured("ROLE_ADMIN")
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @DeleteMapping("/delete_role_from_user")
  @ResponseStatus(HttpStatus.OK)
  public List<Users> deleteRole(@RequestParam Long userId, @RequestParam SystemRoles deletedRole) {

    if (!usersRepository.existsById(userId)) {
      throw new EntityNotFoundException(String.format("There is no user with id = %d", userId));
    }

    Optional<Users> user = usersRepository.findById(userId);

    List<SystemRoles> collectedRoles =
        user.get().getRoles().stream().map(Role::getRoleName).collect(Collectors.toList());

    if (!collectedRoles.contains(deletedRole)) {
      throw new EntityNotFoundException("User doesn't have such role");
    }

    roleRepository.deleteQuery(userId, deletedRole.name());

    return usersRepository.findAll();
  }
}
