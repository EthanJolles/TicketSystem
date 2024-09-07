package com.cmsc495.ticketsystem.controller;

import com.cmsc495.ticketsystem.model.TicketModel;
import com.cmsc495.ticketsystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // Display all tickets
    @GetMapping("/ticket")
    public String viewTickets(Model model) {
        List<TicketModel> ticketModelList = ticketService.findAllTickets();
        model.addAttribute("tickets", ticketModelList);
        return "ticket";
    }

    // View individual ticket details
    @GetMapping("/ticket-details/{id}")
    public String viewTicketDetails(@PathVariable Long id, Model model) {
        TicketModel ticket = ticketService.findTicketById(id);
        model.addAttribute("ticket", ticket);
        return "ticket-details"; // Refers to ticket-details.html
    }

    // Update ticket status and add troubleshooting notes
    @PostMapping("/ticket-details/update")
    public String updateTicketDetails(@RequestParam Long id, 
                                      @RequestParam String status, 
                                      @RequestParam String troubleshootingNotes) {
        TicketModel ticket = ticketService.findTicketById(id);
        ticket.setStatus(status);
        ticket.setTroubleshootingNotes(troubleshootingNotes);
        ticketService.saveTicket(ticket);
        return "redirect:/admin"; // Redirect back to admin dashboard after update
    }

    @PostMapping("/ticket/update")
    public String updateTicketStatus(@RequestParam Long id, 
                                     @RequestParam String status, 
                                     @RequestParam String notes) {
        // Find the ticket by its ID
        TicketModel ticket = ticketService.findTicketById(id);
    
        // Update the status and troubleshooting notes
        ticket.setStatus(status);
        ticket.setTroubleshootingNotes(notes);
    
        // Save the updated ticket back to the database
        ticketService.saveTicket(ticket);
    
    // Redirect back to the admin dashboard after updating
    return "redirect:/admin";
}
}
