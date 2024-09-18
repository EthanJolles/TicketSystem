/* ITMS - A CMSC 495 Project
 * Group 2
 * This is the AdminController class.
 * This class handles the request for the admin page. 
 * It uses the TicketService to retrieve all the tickets from the database
 * and adds them to the model, making them available to the admin view.
 */

package com.cmsc495.ticketsystem.controller.admin;

import com.cmsc495.ticketsystem.model.TicketModel;
import com.cmsc495.ticketsystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        List<TicketModel> ticketModelList = ticketService.findAllTickets();
        model.addAttribute("tickets", ticketModelList);
        return "admin";
    }

}