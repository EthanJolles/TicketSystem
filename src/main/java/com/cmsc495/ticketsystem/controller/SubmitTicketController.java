package com.cmsc495.ticketsystem.controller;
import com.cmsc495.ticketsystem.model.TicketModel;
import com.cmsc495.ticketsystem.service.EmailService;
import com.cmsc495.ticketsystem.service.TicketService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
public class SubmitTicketController {

    @Autowired
    TicketService ticketService;

    @Autowired
    EmailService emailService;

    @GetMapping("/")
    public String showSubmitTicketPage() {
        return "submit";
    }

    @PostMapping("/submit")
    public String submitTicket(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String department,
                               @RequestParam String issueType,
                               @RequestParam String description,
                               Model model) {

        // Create and save ticket object
        TicketModel ticket = new TicketModel(name, email, department, issueType, description);
        ticketService.saveTicket(ticket);

        // Prep subject
        String subject = department + issueType + ticket.getCreationDate();

        // Send confirmation email
        emailService.sendEmail(email, subject, "Ticket received!");

        // Redirect or return a view
        return "redirect:/";
    }
}
