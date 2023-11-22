package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    // @Autowired
    // private UserDetailsService userDetailsService;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeHttpRequests((authz) -> {
            authz.antMatchers(HttpMethod.POST, "/login").permitAll().anyRequest().;
        });
        // @formatter:on

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws
    // Exception {
    // auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    // }
}
