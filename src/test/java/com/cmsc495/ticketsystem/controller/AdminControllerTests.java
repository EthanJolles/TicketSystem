package com.cmsc495.ticketsystem.controller;

import com.cmsc495.ticketsystem.config.BaseTestConfig;
import org.junit.jupiter.api.Test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AdminControllerTests extends BaseTestConfig {

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
