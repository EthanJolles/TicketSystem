/* ITMS - A CMSC 495 Project
 * Group 2
 * 07 SEP 24
 * This is the AdminController class.
 * This class handles the request for the admin page. 
 * It uses the TicketService to retrieve all the tickets from the database
 * and adds them to the model, making them available to the admin view.
 */

package com.cmsc495.ticketsystem.controller;

import com.cmsc495.ticketsystem.model.MyUser;
import com.cmsc495.ticketsystem.model.TicketModel;
import com.cmsc495.ticketsystem.service.MyUserDetailService;
import com.cmsc495.ticketsystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        List<TicketModel> ticketModelList = ticketService.findAllTickets();
        model.addAttribute("tickets", ticketModelList);
        return "admin";
    }

    /*
     * navigates to manage-users page and uses myUserDetailService to retrieve all
     * the users from the database
     * and adds them to the myUser model, making them available to the manage-users
     * view
     */
    @GetMapping("/admin/manage-users")
    public String showManageUsersPage(Model model) {
        List<MyUser> myUserList = myUserDetailService.findAllUsers();
        model.addAttribute("myUserList", myUserList);
        return "manage-users";
    }

    /*
     * Creates and saves user to database when user created and stays on same page
     * after submit
     */
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