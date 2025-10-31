package com.StudentsManagementSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.StudentsManagementSystem.service.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(); // ✅ Load users from DB
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // ✅ (Use BCrypt later)
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/register", "/process-register",
                                 "/css/**", "/js/**", "/images/**").permitAll() // ✅ ALLOWED PAGES
                .anyRequest().authenticated() // ✅ Everything else requires login
            )
            .formLogin(login -> login
                .loginPage("/login")
                .loginProcessingUrl("/process-login") // ✅ login form action
                .defaultSuccessUrl("/home", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        http.authenticationProvider(authenticationProvider()); // ✅ Very Important

        return http.build();
    }
}
