package by.mybrik.security.filters;

import by.mybrik.domain.Role;
import by.mybrik.domain.SystemRoles;
import by.mybrik.repository.impl.UsersRepository;
import by.mybrik.security.CustomHeaders;
import by.mybrik.security.service.UserServiceProvider;
import by.mybrik.security.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final TokenUtils tokenUtils;

    private final UsersRepository usersRepository;

//    @Autowired
//    UserServiceProvider  userServiceProvider;

//    public JwtTokenFilter(TokenUtils tokenUtils) {
//        this.tokenUtils = tokenUtils;
//    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

//        final String authorizationHeader  = request.getHeader(CustomHeaders.AUTH_HEADER);
        final String jwt  = request.getHeader(CustomHeaders.AUTH_HEADER);


        String login = null;
//        String jwt = null;

        if (jwt != null){
            login = tokenUtils.getUsernameFromToken(jwt);
        }

        if (login != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            String commaSeparatedListOfAuthorities = tokenUtils.extractAuthorities(jwt);


            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(usersRepository.findByLogin(login).get().getRoles().stream()
                    .map(Role::getRoleName).map(SystemRoles::name)
                    .collect(Collectors.joining(",")));


//            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(commaSeparatedListOfAuthorities);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            login, null,
//                            authorities
                            grantedAuthorities);

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request, response);

    }
}
