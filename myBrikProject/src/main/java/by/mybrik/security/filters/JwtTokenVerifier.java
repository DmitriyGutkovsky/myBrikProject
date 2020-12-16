package by.mybrik.security.filters;

import by.mybrik.security.CustomHeaders;
import by.mybrik.security.configuration.JwtTokenConfig;
import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JwtTokenVerifier extends OncePerRequestFilter {

    private  final SecretKey secretKey;

    private final JwtTokenConfig jwtConfig;


    /*
    calls filter one per every single request from the client
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(CustomHeaders.AUTH_HEADER);

        // checking that Token is not empty
        if (Strings.isNullOrEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        try{
      //            String secretKey =
      // "securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecure";

      Jws<Claims> claimsJws =
          Jwts.parser()
                                  .setSigningKey(secretKey)
//              .setSigningKey(jwtConfig.getSecret())
              .parseClaimsJws(token);

        }
        catch (JwtException e) {
            throw new IllegalStateException(String.format("Token %s cannot be truest", token));
        }

        filterChain.doFilter(request, response);
    }

    }
//}
