package by.mybrik.security.controller;

import by.mybrik.security.model.AuthRequest;
import by.mybrik.security.model.AuthResponse;
import by.mybrik.security.util.TokenUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/*
user login via existing credentials
 */
@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;

  private final TokenUtils tokenUtils;

  private final UserDetailsService userProvider;

  @ApiOperation(value = "login user in the system", notes = "Return Auth-Token with user login")
  @ApiResponses({
            @ApiResponse(code = 200, message = "Successful authorization"),
            @ApiResponse(code = 400, message = "Request error"),
            @ApiResponse(code = 500, message = "Server error")
    })
  @PostMapping
  public ResponseEntity<AuthResponse> loginUser(@RequestBody AuthRequest request) {

    /*Check login and password*/
    Authentication authenticate =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword())
        );
    SecurityContextHolder.getContext().setAuthentication(authenticate);

    /*Generate token with answer to user*/
    return ResponseEntity.ok(
        AuthResponse
                .builder()
                .username(request.getLogin())
                .token(tokenUtils.generateToken(userProvider.loadUserByUsername(request.getLogin())))
                .build());
  }
}
