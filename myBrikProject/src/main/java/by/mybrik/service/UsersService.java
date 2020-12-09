package by.mybrik.service;


import by.mybrik.domain.Users;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    Users save(Users user);

    List<Users> findAll();

    Users findById(Long id);

    Optional<Users> findOne(Long id);

    Users update(Users user);

    Long delete(Users user);

}
