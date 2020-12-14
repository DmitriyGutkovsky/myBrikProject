package by.mybrik.security.controller;

import by.mybrik.controllers.requests.usersRequests.UserCreate;
import by.mybrik.domain.Role;
import by.mybrik.domain.SystemRoles;
import by.mybrik.domain.Users;
import by.mybrik.repository.impl.UsersRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    @ApiOperation(value = "End point for registration users")
    @PostMapping
    public ResponseEntity<Map<String, Object>> registration(@RequestBody UserCreate request){
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

        Users savedUser = usersRepository.save(user);

        Map<String, Object> result = new HashMap<>();

        result.put("id", savedUser.getId());
        result.put("login", savedUser.getLogin());

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
