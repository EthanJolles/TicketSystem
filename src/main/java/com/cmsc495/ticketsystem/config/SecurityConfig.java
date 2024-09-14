package com.cmsc495.ticketsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Jolles - Disable CSRF protection - NOT recommended for production apps
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/public/**", "/css/**", "/js/**", "/images/**", "/favicon.ico", "/favicon-32x32.png", "/favicon-16x16.png", "/site.webmanifest", "/webjars/**").permitAll()  // Allow public access to these URLs
                .requestMatchers("/admin/**", "/service/**").authenticated()  // Secure the /admin/** and /service/** endpoints
            )
            .httpBasic(Customizer.withDefaults());  // Enable Basic Authentication

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Jolles - Normally this would be environmental variables not hardcoded
        UserDetails user = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
