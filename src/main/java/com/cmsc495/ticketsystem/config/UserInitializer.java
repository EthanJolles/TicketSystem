package com.cmsc495.ticketsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.cmsc495.ticketsystem.model.MyUser;
import com.cmsc495.ticketsystem.repository.MyUserRepository;

@Configuration
public class UserInitializer {

    @Autowired
    private MyUserRepository myUserRepository;

    @Bean
    public CommandLineRunner createFirstUser() {
        return args -> {
            if (myUserRepository.count() == 0) {
                // No users found, so create the first user
                MyUser firstUser = new MyUser("admin", "$2a$12$QpKyxU1mahx7MwwL0zkU4.Rvc7JoGBwvDkk.L9fKFFhcfFQbrFzYO");
                myUserRepository.save(firstUser);
                System.out.println("First user 'admin' created with password 'admin'. Initial user should be deleted once other users established.");
            }
        };
    }
}