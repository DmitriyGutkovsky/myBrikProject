package by.mybrik.converters.users;

import by.mybrik.controllers.requests.usersRequests.UserCreate;
import by.mybrik.domain.Users;
import org.springframework.core.convert.converter.Converter;

public abstract class UsersConverter<S, T> implements Converter<S, T> {

  protected Users doConvert(Users user, UserCreate request) {

    user.setName(request.getName());
    user.setSurName(request.getSurName());
    user.setLogin(request.getLogin());
    user.setEmail(request.getEmail());
    user.setGender(request.getGender());
    user.setPhone(request.getPhone());
    user.setAddress(request.getAddress());
    user.setDeleted(request.isDeleted());

    return user;
  }
}
