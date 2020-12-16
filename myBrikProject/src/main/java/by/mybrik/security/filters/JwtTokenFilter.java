package by.mybrik.security.filters;

import by.mybrik.security.CustomHeaders;
import by.mybrik.security.service.UserServiceProvider;
import by.mybrik.security.util.TokenUtils;
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

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final TokenUtils tokenUtils;

    @Autowired
    UserServiceProvider  userServiceProvider;

    public JwtTokenFilter(TokenUtils tokenUtils) {
        this.tokenUtils = tokenUtils;
    }


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
            String commaSeparatedListOfAuthorities = tokenUtils.extractAuthorities(jwt);

            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(commaSeparatedListOfAuthorities);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            login, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request, response);

    }
}
