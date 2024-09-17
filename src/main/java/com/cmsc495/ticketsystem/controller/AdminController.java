/* ITMS - A CMSC 495 Project
 * Group 2
 * 07 SEP 24
 * This is the AdminController class.
 * This class handles the request for the admin page.
 * It uses the TicketService to retrieve all the tickets from the database
 * and adds them to the model, making them available to the admin view.
 * Additionally, it sends an email notification when a ticket is closed.
 */

package com.cmsc495.ticketsystem.controller;

import com.cmsc495.ticketsystem.model.TicketModel;
import com.cmsc495.ticketsystem.service.TicketService;
import com.cmsc495.ticketsystem.service.EmailService;
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
    private EmailService emailService;  // Email service to send email notifications

    // Display all tickets on the admin page
    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        List<TicketModel> ticketModelList = ticketService.findAllTickets();
        model.addAttribute("tickets", ticketModelList);
        return "admin";
    }

    // Method to close a ticket and send an email notification
    @PostMapping("/close-ticket/{ticketId}")
    public String closeTicket(@PathVariable Long ticketId, @RequestParam String noteContent, Model model) {
        // Close the ticket by updating its status to "Closed"
        TicketModel closedTicket = ticketService.changeTicketAndStatus(ticketId, "Closed", noteContent);

        // Send an email to notify the submitter about the closed ticket
        boolean emailSent = sendClosureNotificationEmail(closedTicket);
        if (!emailSent) {
            model.addAttribute("emailError", "Failed to send email notification for Ticket ID: " + ticketId);
        }

        // Redirect back to the admin page after closing the ticket
        return "redirect:/admin";
    }

    // Helper method to send an email notification when a ticket is closed
    private boolean sendClosureNotificationEmail(TicketModel ticket) {
        String email = ticket.getEmail();  // Get the submitter's email address
        String subject = "Ticket #" + ticket.getId() + " Closed";  // Subject of the email
        String body = "Hello " + ticket.getName() + ",\n\n" +
                "Your ticket with ID #" + ticket.getId() + " has been closed.\n\n" +
                "Troubleshooting Notes: " + ticket.getTroubleshootingNotes() + "\n\n" +
                "Thank you for using our service.\n\nBest regards,\nSupport Team";

        // Use the EmailService to send the email and return the status
        try {
            return emailService.sendEmail(email, subject, body);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Redirect back to the admin page after closing the ticket
    }

