package org.example.tz4krainet.Config;

import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.example.tz4krainet.Security.JWTRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final JWTRequestFilter jwtRequestFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        return security.csrf().disable()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((request)-> request
                        .requestMatchers("/admin/create/project").hasRole("ADMIN")
                        .requestMatchers("/admin/create/record").hasRole("ADMIN")
                        .requestMatchers("/user/change/profile").hasAnyRole("ADMIN","USER")
                        .requestMatchers("/user/delete").hasAnyRole("ADMIN","USER")
                        .requestMatchers("/project/change/profile").hasAnyRole("ADMIN")
                        .requestMatchers("/project/delete").hasAnyRole("ADMIN")
                        .requestMatchers("/admin/delete/record").hasAnyRole("ADMIN")
                        .requestMatchers("/user/change/record").hasAnyRole("ADMIN","USER")
                        .requestMatchers("/admin/project/find/all").authenticated()
                        .requestMatchers("/auth/reg").permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        .anyRequest().authenticated())
                .build();
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
