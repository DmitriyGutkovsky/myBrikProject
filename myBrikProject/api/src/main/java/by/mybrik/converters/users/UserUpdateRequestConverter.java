package by.mybrik.converters.users;

import by.mybrik.controllers.requests.usersRequests.UsersUpdate;
import by.mybrik.domain.Users;
import by.mybrik.exceptions.EntityNotFoundException;
import by.mybrik.repository.impl.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class UserUpdateRequestConverter extends UsersConverter<UsersUpdate, Users> {

  public final PasswordEncoder passwordEncoder;

  public final UsersRepository usersRepository;

  @Override
  public Users convert(UsersUpdate request) {
    Users user =
        usersRepository
            .findById(request.getId())
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        String.format("There is no user with id = %d", request.getId())));
    user.setChanged(new Timestamp(System.currentTimeMillis()));
    return doConvert(user, request);
  }
}
