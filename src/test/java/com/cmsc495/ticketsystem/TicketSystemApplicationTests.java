package com.cmsc495.ticketsystem;

import org.junit.jupiter.api.Test;
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

    @Test
    void contextLoads() {
        // This test will fail if the application context cannot start
    }

    @Test
    public void testRootRedirectsToPublic() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/public"));
    }

    @Test
    public void testPublicEndpointReturnsHtml() throws Exception {
        mockMvc.perform(get("/public"))
                .andExpect(status().isOk())  // Expect status 200 OK
                .andExpect(view().name("public"));  // Expect the view name to be "public"
    }

}
