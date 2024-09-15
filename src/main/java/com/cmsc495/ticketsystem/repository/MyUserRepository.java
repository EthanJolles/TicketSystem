package com.cmsc495.ticketsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmsc495.ticketsystem.model.MyUser;
import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByUsername(String username);
}