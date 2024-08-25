package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SubmitTicketController {

    @GetMapping("/")
    public String submitTicket() {
        return "index";  // Redirect to the static HTML file
    }
}
