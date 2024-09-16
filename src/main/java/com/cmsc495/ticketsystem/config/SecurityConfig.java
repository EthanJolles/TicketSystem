package com.cmsc495.ticketsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.cmsc495.ticketsystem.service.MyUserDetailService;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Autowired
        private MyUserDetailService userDetailService;

        @Autowired
        private PasswordEncoder passwordEncoder;
    /*
     * @Bean
     * public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
     * Exception {
     * http
     * .csrf().disable() // Jolles - Disable CSRF protection - NOT recommended for
     * production apps
     * .authorizeHttpRequests(authorize -> authorize
     * .requestMatchers("/", "/public/**", "/css/**").permitAll() // Allow public
     * access to these URLs
     * .requestMatchers("/admin/**", "/service/**").authenticated() // Secure the
     * /secure/** endpoints
     * )
     * .httpBasic(Customizer.withDefaults()); // Enable Basic Authentication
     * 
     * return http.build();
     * }
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/public/**", "/css/**", "/login", "/logout"/js/**", "/images/**", "/favicon.ico", "/favicon-32x32.png", "/favicon-16x16.png", "/site.webmanifest", "/webjars/**").permitAll() // Allow public access to these URLs
                        .requestMatchers("/admin/**", "/service/**").authenticated() // Secure the /secure/** endpoints
                        .anyRequest().authenticated()
                )
                .formLogin(httpSecurityFormLoginConfigurer -> {
                        httpSecurityFormLoginConfigurer.loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/admin", true); //redirects to admin upon successful login
                })
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login") // Redirect to /login after successful logout
                        .invalidateHttpSession(true) // Invalidate the session
                        .deleteCookies("JSESSIONID") // Delete the session cookie
                        .permitAll()
                );

        return httpSecurity.build();
    }

    /*
     * @Bean
     * public UserDetailsService userDetailsService() {
     * // Jolles - Normally this would be environmental variables not hardcoded
     * UserDetails user = User.builder()
     * .username("admin")
     * .password(passwordEncoder().encode("admin"))
     * .roles("USER")
     * .build();
     * 
     * return new InMemoryUserDetailsManager(user);
     * }
     */

    /*@Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("admin")
                .password("$2a$12$.XodfXx.ab2ShnZBee3I5e.QXPCZAcgWEVOqPl.T5/61A.EfCO1rK")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }*/

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}
