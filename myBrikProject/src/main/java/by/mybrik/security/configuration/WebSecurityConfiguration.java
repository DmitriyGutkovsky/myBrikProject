package by.mybrik.security.configuration;

import by.mybrik.security.filters.JwtTokenFilter;
import by.mybrik.security.service.UserServiceProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final UserServiceProvider userServiceProvider;

  @Autowired private JwtTokenFilter jwtFilter;

  @Autowired
  public void configureAuthentication(
      AuthenticationManagerBuilder authenticationManagerBuilder, PasswordEncoder passwordEncoder)
      throws Exception {
    authenticationManagerBuilder
        .userDetailsService(userServiceProvider)
        .passwordEncoder(passwordEncoder);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .csrf()
        .disable()
        .exceptionHandling()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()

        /*For swagger access only*/
        .antMatchers(
            "/v2/api-docs",
            "/configuration/ui/**",
            "/swagger-resources/**",
            "/configuration/security/**",
            "/swagger-ui.html",
            "/webjars/**").permitAll()
        .antMatchers("/actuator/**").permitAll()
        .antMatchers(HttpMethod.GET, "/swagger-ui.html#").permitAll()

        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

        .antMatchers("/registration/**").permitAll()
        .antMatchers("/authentication/**").permitAll()
        .antMatchers("/new/rest/goods").permitAll()
        .antMatchers("/new/rest/goods/*").permitAll()
        .antMatchers("/new/rest/individualorderprice").permitAll()
        .antMatchers("/new/rest/individualorderprice/*").permitAll()

        .anyRequest()
        .authenticated();

    httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
  }

  // For swagger access only
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers(
            "/v2/api-docs",
            "/configuration/ui/**",
            "/swagger-resources/**",
            "/configuration/security/**",
            "/swagger-ui.html",
            "/webjars/**");
  }
}
