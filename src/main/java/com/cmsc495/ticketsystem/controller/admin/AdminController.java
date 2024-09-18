/* ITMS - A CMSC 495 Project
 * Group 2
 * This is the AdminController class.
 * This class handles the request for the admin page.
 * It uses the TicketService to retrieve all the tickets from the database
 * and adds them to the model, making them available to the admin view.
 * Additionally, it sends an email notification when a ticket is closed.
 */

package com.cmsc495.ticketsystem.controller.admin;

import com.cmsc495.ticketsystem.model.TicketModel;
import com.cmsc495.ticketsystem.service.TicketService;
import com.cmsc495.ticketsystem.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EmailService emailService;  // Email service to send email notifications

    // Display all tickets on the admin page
    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        List<TicketModel> ticketModelList = ticketService.findAllTickets();
        model.addAttribute("tickets", ticketModelList);
        return "admin";
    }
}
