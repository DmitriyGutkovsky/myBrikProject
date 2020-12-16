package by.mybrik.security.filters;

import by.mybrik.security.CustomHeaders;
import by.mybrik.security.model.AuthRequest;
import by.mybrik.security.model.AuthResponse;
import by.mybrik.security.util.TokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final TokenUtils tokenUtils;

    private final UserDetailsService userProvider;



    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            AuthRequest authRequest = new ObjectMapper() // rad valur from the InputStream and put value into AuthRequest.class
                    .readValue(request.getInputStream(), AuthRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authRequest.getLogin(),
                    authRequest.getPassword()
            );
            // authenticationManager checks is the user exists and
            // if user exists check password is correct or not
            Authentication authenticate = authenticationManager.authenticate(authentication);
            return authenticate;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

/*
this method will be invoke after attemptAuthentication() is successful
 */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {

//        Jwts.builder()
//                .setSubject(authResult.getName())
//                .token(tokenUtils.generateToken(userProvider.loadUserByUsername(request.getLogin())))

        AuthRequest authRequest = new ObjectMapper() // rad valur from the InputStream and put value into AuthRequest.class
                .readValue(request.getInputStream(), AuthRequest.class);

        AuthResponse build = AuthResponse
                .builder()
                .username(authRequest.getLogin())
                .token(tokenUtils.generateToken(userProvider.loadUserByUsername(authRequest.getLogin())))
                .build();

        response.addHeader(CustomHeaders.AUTH_HEADER, String.valueOf(build));

//        super.successfulAuthentication(request, response, chain, authResult);
    }
}
