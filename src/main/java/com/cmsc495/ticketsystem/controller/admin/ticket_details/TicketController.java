/* ITMS - A CMSC 495 Project
 * Group 2
 * This is the TicketController class.
 * This class handles requests related to ticket management for the admin. 
 * It provides functionality for viewing individual ticket details and updating ticket status,
 * including adding troubleshooting notes.
 */

package com.cmsc495.ticketsystem.controller.admin.ticket_details;

import com.cmsc495.ticketsystem.model.TicketModel;
import com.cmsc495.ticketsystem.service.EmailService;
import com.cmsc495.ticketsystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EmailService emailService;

    // View individual ticket details
    @GetMapping("/ticket-details/{id}")
    public String viewTicketDetails(@PathVariable Long id, Model model) {
        TicketModel ticket = ticketService.findTicketById(id);
        model.addAttribute("ticket", ticket);
        return "ticket-details"; // Refers to ticket-details.html
    }

    // Update ticket status and add troubleshooting notes
    @PostMapping("/ticket/update")
    public String updateTicketDetails(@RequestParam Long id, 
                                      @RequestParam String status, 
                                      @RequestParam String notes,
                                      @RequestParam(required = false) boolean remove) {
        TicketModel ticket = ticketService.changeTicketAndStatus(id, status, notes);

        if (remove) {
        // If the remove checkbox is selected, delete the ticket from the database
            this.sendClosureEmail(ticket);
            ticketService.deleteTicketById(id);
        }

        return "redirect:/admin"; // Redirect back to admin dashboard after update
    }

    private void sendClosureEmail(TicketModel ticket) {
        String subject = "Ticket " + ticket.getDepartment() + " " + ticket.getIssueType() + " " + ticket.getFormattedDate() + " has been resolved";
        String body = """
                    Hello %s,
                    
                    We are pleased to inform you that your ticket has been resolved.
                    
                    Notes from IT - %s
                    
                    Please submit a new ticket if there are any further issues.
                    
                    Sincerely,
                    ITMS
                    """.formatted(ticket.getName(), ticket.getTroubleshootingNotes());
        try {
            emailService.sendEmail(ticket.getEmail(), subject, body);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 }
