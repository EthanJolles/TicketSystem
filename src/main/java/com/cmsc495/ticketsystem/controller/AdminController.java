package com.cmsc495.ticketsystem.controller;

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
//        List<TicketModel> ticketModelList = ticketService.findAllTickets();
//        model.addAttribute("tickets", ticketModelList);
        return "admin";
    }
}
