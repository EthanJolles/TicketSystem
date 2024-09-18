package com.cmsc495.ticketsystem;

import com.cmsc495.ticketsystem.model.TicketModel;
import com.cmsc495.ticketsystem.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TicketSystemApplicationTests {

    @Autowired
    MockMvc mockMvc;
    
    @Autowired
    TicketRepository ticketRepository;

    @Test
    void contextLoads() {
        // This test will fail if the application context cannot start
    }

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
                .andExpect(status().is2xxSuccessful());

        TicketModel savedTicket = ticketRepository.findAll().stream()
                .filter(ticket -> "UMGC@example.com"
                .equals(ticket.getEmail()))
                .findFirst()
                .orElse(null);

        assertThat(savedTicket).isNotNull();
        assertThat(savedTicket.getName()).isEqualTo("University Maryland");
    }

    @Test
    // Expect that without authentication /admin endpoint will be inaccessible
    public void testAdminEndpointUnauthenticatedReturnsError() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    // Expect that with authentication /admin endpoint will return "admin" view
    public void testAdminEndpointReturnsHtml() throws Exception {
        mockMvc.perform(get("/admin").with(user("admin").password("password").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"));
    }
}
