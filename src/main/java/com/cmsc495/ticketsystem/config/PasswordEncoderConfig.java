/* ITMS - A CMSC 495 Project
 * Group 2
 *Configuration file to encrypt password. 
 *Used by MyUserDetailService to encrypt password before storage in database
 *Used by SecurityConfig when user enters password before password comparison
 */

package com.cmsc495.ticketsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
