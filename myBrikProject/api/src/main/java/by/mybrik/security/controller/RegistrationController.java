package by.mybrik.security.controller;

import by.mybrik.controllers.requests.usersRequests.UserCreate;
import by.mybrik.domain.Users;
import by.mybrik.repository.impl.UsersRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

  private final UsersRepository usersRepository;

  public final JavaMailSender emailSender;

  public final ConversionService conversionService;

  @ApiOperation(value = "End point for registration users")
  @PostMapping
  public ResponseEntity<Map<String, Object>> registration(@RequestBody UserCreate request) {

    Users user = conversionService.convert(request, Users.class);

    Users savedUser = usersRepository.save(user);

    Map<String, Object> result = new HashMap<>();

    result.put("id", savedUser.getId());
    result.put("login", savedUser.getLogin());

    sendWelcomeMessage(user.getEmail());

    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }

  public void sendWelcomeMessage(String email) {
    // Create a Simple MailMessage.
    SimpleMailMessage message = new SimpleMailMessage();

    message.setTo(email);
    message.setSubject("Shop \"Brik\" - registration!");
    message.setText("Congratulation! You have successfully registered into shop \"Brik\"!");

    // Send Message
    this.emailSender.send(message);
  }
}
