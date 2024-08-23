package controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class SubmitTicketController {

    @GetMapping("/")
    public String submitTicket() {
        return "submitticket";
    }
}