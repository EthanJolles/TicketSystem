/* ITMS - A CMSC 495 Project
 * Group 2
 * This interface extends JpaRepository and provides CRUD operations for the MyUser entity.
 * It allows interaction with the database to perform actions such as retrieving, saving, updating,
 * and deleting myUser records by leveraging Spring Data JPA's built-in methods. 
 */

package com.cmsc495.ticketsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmsc495.ticketsystem.model.MyUserModel;
import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUserModel, Long> {
    Optional<MyUserModel> findByUsername(String username);
}