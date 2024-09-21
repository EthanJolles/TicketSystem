package com.cmsc495.ticketsystem.controller;

import com.cmsc495.ticketsystem.config.BaseTestConfig;
import com.cmsc495.ticketsystem.model.TicketModel;
import com.cmsc495.ticketsystem.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SubmitTicketControllerTests extends BaseTestConfig {

    @Autowired
    TicketRepository ticketRepository;

    @Test
    // Expect accessing the base url will redirect to the /public endpoint
    public void testRootRedirectsToPublic() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/public"));
    }

    @Test
    public void testPublicEndpointReturnsHtml() throws Exception {
        mockMvc.perform(get("/public"))
                .andExpect(status().isOk())  // Expect status 200 OK
                .andExpect(view().name("submit"));  // Expect the view name to be "public"
    }

    @Test
    // Expect redirect to happen after submitting ticket and sending post request.
    // Expect ticket to be saved and fields to be correct
    public void testSubmitTicketIntegration() throws Exception {
        mockMvc.perform(post("/public/submit")
                        .param("name", "University Maryland")
                        .param("email", "UMGC@example.com")
                        .param("department", "IT")
                        .param("issueType", "Software")
                        .param("description", "Unable to install"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/public"));

        TicketModel savedTicket = ticketRepository.findAll().stream()
                .filter(ticket -> "UMGC@example.com"
                        .equals(ticket.getEmail()))
                .findFirst()
                .orElse(null);

        assertThat(savedTicket).isNotNull();
        assertThat(savedTicket.getName()).isEqualTo("University Maryland");
    }
}
