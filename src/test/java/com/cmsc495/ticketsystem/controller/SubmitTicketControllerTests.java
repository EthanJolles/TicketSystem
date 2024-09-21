package com.cmsc495.ticketsystem.controller;

import com.cmsc495.ticketsystem.config.BaseTestConfig;
import com.cmsc495.ticketsystem.model.TicketModel;
import com.cmsc495.ticketsystem.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SubmitTicketControllerTests extends BaseTestConfig {

    @MockBean
    TicketRepository ticketRepository;  // Mock the repository

    @Test
    public void testRootRedirectsToPublic() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/public"));
    }

    @Test
    public void testPublicEndpointReturnsHtml() throws Exception {
        mockMvc.perform(get("/public"))
                .andExpect(status().isOk())
                .andExpect(view().name("submit"));
    }

    @Test
    public void testSubmitTicketIntegration() throws Exception {
        // Arrange: Mock save behavior
        when(ticketRepository.save(any(TicketModel.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act: Perform the post request
        mockMvc.perform(post("/public/submit")
                        .param("name", "University Maryland")
                        .param("email", "UMGC@example.com")
                        .param("department", "IT")
                        .param("issueType", "Software")
                        .param("description", "Unable to install")
                        .with(csrf()))  // Include CSRF token
                .andExpect(status().is2xxSuccessful());

        // Assert: Verify ticket save call
        Mockito.verify(ticketRepository).save(any(TicketModel.class));
    }
}
