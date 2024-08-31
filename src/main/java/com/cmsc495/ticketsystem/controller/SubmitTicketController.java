package com.cmsc495.ticketsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SubmitTicketController {

    @GetMapping("/")
    public String showSubmitTicketPage() {
        return "submit";
    }
}
