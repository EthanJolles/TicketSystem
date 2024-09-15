/* ITMS - A CMSC 495 Project
 * Group 2
 * Last updated 14 SEP 24
 * This is the SubmitTicketController class.
 * This class handles the submission of IT tickets from the public page.
 * It uses the TicketService to save ticket information to the database
 * and the EmailService to send a confirmation email upon successful submission.
 * The submitTicket method processes the POST request and returns an appropriate
 * HTTP response for success or failure, allowing the frontend to display messages.
 */
package com.cmsc495.ticketsystem.controller;

import com.cmsc495.ticketsystem.model.TicketModel;
import com.cmsc495.ticketsystem.service.EmailService;
import com.cmsc495.ticketsystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SubmitTicketController {

    @Autowired
    TicketService ticketService;

    @Autowired
    EmailService emailService;

    @GetMapping("/")
    public String redirectToSubmitTicketPage() {
        return "redirect:/public";
    }

    @GetMapping("/public")
    public String showSubmitTicketPage() {
        return "submit";
    }

    @PostMapping("/public/submit")
    public ResponseEntity<String> submitTicket(@RequestParam String name,
                                               @RequestParam String email,
                                               @RequestParam String department,
                                               @RequestParam String issueType,
                                               @RequestParam String description) {

        try {
            // Create and save ticket object
            TicketModel ticket = new TicketModel(name, email, department, issueType, description);
            ticketService.saveTicket(ticket);

            // Send email notification (optional)
            sendEmail(ticket, email, department, issueType);

            // Return a success message with HTTP status 200 OK
            return ResponseEntity.ok("Ticket submitted successfully!");

        } catch (Exception e) {
            // Return an error message with HTTP status 500 INTERNAL_SERVER_ERROR
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error submitting the ticket.");
        }
    }

    private void sendEmail(TicketModel ticketModel, String email, String department, String issueType) {
        try {
            emailService.sendEmail(email, department + issueType + ticketModel.getFormattedDate(), "Ticket submitted! A tech will get to work on your ticket immediately. We will keep you updated with the status of your ticket until it is closed. Thank you for your patience.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
