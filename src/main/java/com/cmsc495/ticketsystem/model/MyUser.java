/* ITMS - A CMSC 495 Project
 * Group 2
 * MyUser class represents a user and is used define and create myUser objects 
 */

package com.cmsc495.ticketsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //id automatically generated for use as key in database
    private Long id;
    private String username;
    private String password;
    private String role = "User";

    public MyUser() {};

    public MyUser (String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
