package by.mybrik.converters.users;

import by.mybrik.controllers.requests.usersRequests.UserCreate;
import by.mybrik.domain.Role;
import by.mybrik.domain.SystemRoles;
import by.mybrik.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class UserCreateRequestConverter extends UsersConverter<UserCreate, Users> {

  public final PasswordEncoder passwordEncoder;

  @Override
  public Users convert(UserCreate request) {

    Users user = new Users();

    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setCreated(new Timestamp(System.currentTimeMillis()));
    user.setChanged(new Timestamp(System.currentTimeMillis()));
    user.setRoles(Collections.singleton(new Role(SystemRoles.ROLE_USER, user)));

    return doConvert(user, request);
  }
}
