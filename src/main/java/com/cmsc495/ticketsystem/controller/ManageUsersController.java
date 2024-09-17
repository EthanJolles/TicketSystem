/* ITMS - A CMSC 495 Project
 * Group 2
 * This class handles requests related to users
 */

package com.cmsc495.ticketsystem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.cmsc495.ticketsystem.model.MyUser;
import com.cmsc495.ticketsystem.service.MyUserDetailService;

@Controller
public class ManageUsersController {

    @Autowired
    private MyUserDetailService myUserDetailService;

    /*navigates to manage-users page and uses myUserDetailService to retrieve all
     * the users from the database
     * and adds them to the myUser model, making them available to the manage-users
     * view*/
    @GetMapping("/admin/manage-users")
    public String showManageUsersPage(Model model) {
        List<MyUser> myUserList = myUserDetailService.findAllUsers();
        model.addAttribute("myUserList", myUserList);
        return "manage-users";
    }

    /* Creates and saves user to database when user created and stays on same page
     * after submit*/
    @PostMapping("/admin/manage-users/submit")
    public String createUser(@RequestParam String username,
            @RequestParam String password,
            Model model) {

        // Create and save MyUser object
        MyUser myUser = new MyUser(username, password);
        myUserDetailService.saveMyUser(myUser);
        return "redirect:/admin/manage-users";
    }

    @PostMapping("/admin/delete-user/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        myUserDetailService.deleteUserById(id); // Call service to delete the user
        return "redirect:/admin/manage-users"; // Redirect back to the manage-users page
    }
}
