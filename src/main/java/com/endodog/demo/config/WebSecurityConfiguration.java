package com.endodog.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

  @Bean
  public UserDetailsService users() {
    UserDetails user = User.builder()
            .username("emp")
            .password(passwordEncoder().encode("1"))
            .roles("EMPLOYEE")
            .build();

    UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("1"))
            .roles("ADMIN")
            .build();

    return new InMemoryUserDetailsManager(user, admin);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    String[] resources = new String[]{
            "/WEB-INF/views/**"
    };

    http.csrf(AbstractHttpConfigurer::disable) // disable csrf
            .authorizeHttpRequests(
                    requests ->
                            requests
                                    .requestMatchers(resources).permitAll() // allow access resources in project
                                    .requestMatchers("/employee/**")
                                    .hasRole("EMPLOYEE")
                                    .requestMatchers("/admin/**")
                                    .hasRole("ADMIN")
                                    .requestMatchers("/user").permitAll()
                                    .anyRequest()
                                    .authenticated())
            .formLogin((form -> {
              form
                      .loginPage("/login")
                      .loginProcessingUrl("/login") // If not use, it defaults auto check login
                      .defaultSuccessUrl("/") // If not use, it defaults to /
//                      .successHandler(myAuthenticationSuccessHandler()) custom handler when login success
                      .failureUrl("/login?error=true")
                      .permitAll();
            }))
            .logout(
                    logout -> logout
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/login") // If not use, it defaults to /login?logout
//                            .invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID")
                            .permitAll());
    return http.build();
  }

}
