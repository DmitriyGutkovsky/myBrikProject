package by.mybrik.security.configuration;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtSecretKey {

    private final JwtTokenConfig jwtTokenConfig;

    @Autowired
    public JwtSecretKey(JwtTokenConfig jwtTokenConfig) {
        this.jwtTokenConfig = jwtTokenConfig;
    }

    @Bean
    public SecretKey secretKey(){
        return Keys.hmacShaKeyFor(jwtTokenConfig.getSecret().getBytes());
    }

}
