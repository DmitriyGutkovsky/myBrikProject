package by.mybrik.service.newImplementation;


import by.mybrik.domain.entities.Users;

import java.util.List;
import java.util.Optional;

public interface UsersSer {

    Users save(Users user);

    List<Users> findAll();

    Users findById(Long id);

    Optional<Users> findOne(Long id);

    Users update(Users user);

    Long delete(Users user);

}
