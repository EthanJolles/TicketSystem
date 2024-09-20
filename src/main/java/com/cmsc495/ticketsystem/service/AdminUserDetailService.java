/* ITMS - A CMSC 495 Project
 * Group 2 
 * This is the MyUserDetailService class.
 * This class acts as the service layer between the controller and the repository.
 * It contains methods to interact with the MyUserRepository for performing user-related operations.
 * These include loading a user by name, saving a user, and finding all users
 */

package com.cmsc495.ticketsystem.service;

import com.cmsc495.ticketsystem.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.cmsc495.ticketsystem.model.AdminUserModel;
import java.util.Optional;
import java.util.List;

@Service
public class AdminUserDetailService implements UserDetailsService {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AdminUserModel> user = adminUserRepository.findByUsername(username);
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

    public String saveAdminUser(AdminUserModel myUser) {
        // Check if the username already exists
        if (adminUserRepository.existsByUsername(myUser.getUsername())) {
            return "Username already exists";
        }

        String encodedPassword = passwordEncoder.encode(myUser.getPassword());  //changes user password to encrypted version to store in database
        myUser.setPassword(encodedPassword);
        adminUserRepository.save(myUser);
        return "User Creation Successful";
    }

    public List<AdminUserModel> findAllUsers() {
        return adminUserRepository.findAll();
    }

    public void deleteUserById(Long id) {
        adminUserRepository.deleteById(id);
    }

}