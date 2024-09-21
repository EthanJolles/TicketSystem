package com.cmsc495.ticketsystem.controller;

import com.cmsc495.ticketsystem.config.BaseTestConfig;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class LoginControllerTests extends BaseTestConfig {

    @Test
    // Expect accessing /login to return the login page
    public void testLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())  // Expect status 200 OK
                .andExpect(view().name("login"));  // Expect the view name to be "login"
    }

    @Test
    // Expect accessing /logout to redirect to the login page
    public void testLogoutRedirectsToLogin() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection())  // Expect 3xx redirect
                .andExpect(redirectedUrl("/login"));  // Expect the redirect URL to be /login
    }
}
