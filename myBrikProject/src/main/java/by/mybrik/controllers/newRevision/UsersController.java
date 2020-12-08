package by.mybrik.controllers.newRevision;

import by.mybrik.domain.entities.Users;
import by.mybrik.service.newImplementation.UsersSer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("new/rest/users")
public class UsersController {

  public final UsersSer usersService;

  // http://localhost:8080/new/rest/users
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Users> getAllUsers() {
    return usersService.findAll();
  }
}
