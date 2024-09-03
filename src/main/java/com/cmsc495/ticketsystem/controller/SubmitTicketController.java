package com.cmsc495.ticketsystem.controller;
import com.cmsc495.ticketsystem.model.TicketModel;
import com.cmsc495.ticketsystem.service.EmailService;
import com.cmsc495.ticketsystem.service.TicketService;
import com.cmsc495.ticketsystem.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SubmitTicketController {

    @Autowired
    TicketService ticketService;

    @Autowired
    TokenService tokenService;

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
    public String submitTicket(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String department,
                               @RequestParam String issueType,
                               @RequestParam String description,
                               Model model) {
        String accessToken = tokenService.refreshAccessToken();

        // Create and save ticket object
        TicketModel ticket = new TicketModel(name, email, department, issueType, description);
        ticketService.saveTicket(ticket);

        // Prep subject
        String subject = department + issueType + ticket.getCreationDate();

        // Send confirmation email
        try {
            emailService.sendEmail(email, subject, "Ticket received!", accessToken);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/public";
    }
}
