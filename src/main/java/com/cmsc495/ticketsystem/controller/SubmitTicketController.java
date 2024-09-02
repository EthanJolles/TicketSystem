package com.cmsc495.ticketsystem.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SubmitTicketController {

    @GetMapping("/")
    public String showSubmitTicketPage() {
        return "submit";

    @Autowired
    private EmailService emailService;

    @Autowired
    private TicketRepository ticketRepository;

    @PostMapping("/submit")
    public String submitTicket(TicketModel ticket) {
        // Save the ticket to the database
        ticketRepository.save(ticket);

        // Send a confirmation email to the user
        try {
            emailService.sendEmail(ticket.getUserEmail(), 
                                   "Ticket Submitted", 
                                   "Your ticket has been successfully submitted.");
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle the email sending error, perhaps show an error message to the user
        }

        // Redirect or return a view
        return "redirect:/tickets/confirmation";
    }
}

    }
}
