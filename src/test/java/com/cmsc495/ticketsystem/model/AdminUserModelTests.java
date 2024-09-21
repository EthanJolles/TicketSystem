package com.cmsc495.ticketsystem.model;

import com.cmsc495.ticketsystem.config.BaseTestConfig;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AdminUserModelTests extends BaseTestConfig {

    @Test
    public void testAdminUserModelCreation() {
        AdminUserModel user = new AdminUserModel("admin", "password123");

        assertThat(user.getUsername()).isEqualTo("admin");
        assertThat(user.getPassword()).isEqualTo("password123");
        assertThat(user.getRole()).isEqualTo("Admin");
    }

    @Test
    public void testSettersAndGetters() {
        AdminUserModel user = new AdminUserModel();
        user.setUsername("admin");
        user.setPassword("password123");
        user.setRole("Admin");
        user.setId(1L);

        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getUsername()).isEqualTo("admin");
        assertThat(user.getPassword()).isEqualTo("password123");
        assertThat(user.getRole()).isEqualTo("Admin");
    }
}
