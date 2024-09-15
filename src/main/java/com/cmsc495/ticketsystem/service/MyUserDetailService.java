package com.cmsc495.ticketsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cmsc495.ticketsystem.model.MyUser;
import com.cmsc495.ticketsystem.repository.MyUserRepository;
import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private MyUserRepository myUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = myUserRepository.findByUsername(username);
        if(user.isPresent()) {
            var userObj = user.get();
            return User.builder()
                .username(userObj.getUsername())
                .password(userObj.getPassword())
                .roles(userObj.getRole())
                .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}