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

@Controller
@RequestMapping("/admin")
public class TicketController {

    @Autowired
    private TicketService ticketService;

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
                                      @RequestParam String notes) {
        ticketService.changeTicketAndStatus(id, status, notes);
        return "redirect:/admin"; // Redirect back to admin dashboard after update
    }
}
