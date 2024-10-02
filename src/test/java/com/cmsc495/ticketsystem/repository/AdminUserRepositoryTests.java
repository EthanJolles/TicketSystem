package com.cmsc495.ticketsystem.repository;

import com.cmsc495.ticketsystem.model.AdminUserModel;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AdminUserRepositoryTests {

    @MockBean
    private AdminUserRepository adminUserRepository;  // Mock the repository

    @Test
    public void testFindByUsernameWithMockData() {
        // Arrange: Create mock user and define repository behavior
        AdminUserModel mockUser = new AdminUserModel("admin", "password123");
        when(adminUserRepository.findByUsername("admin")).thenReturn(Optional.of(mockUser));

        // Act: Call the method with the mocked repository
        Optional<AdminUserModel> foundUser = adminUserRepository.findByUsername("admin");

        // Assert: Verify that the mock user was returned
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("admin");
    }

    @Test
    public void testExistsByUsernameWithMockData() {
        // Arrange: Mock repository behavior to return true for a specific username
        when(adminUserRepository.existsByUsername("admin")).thenReturn(true);

        // Act: Call the method with the mocked repository
        boolean exists = adminUserRepository.existsByUsername("admin");

        // Assert: Verify that the mock method returns true
        assertThat(exists).isTrue();
    }

    @Test
    public void testDoesNotExistByUsernameWithMockData() {
        // Arrange: Mock repository behavior to return false for a specific username
        when(adminUserRepository.existsByUsername("nonexistent")).thenReturn(false);

        // Act: Call the method with the mocked repository
        boolean exists = adminUserRepository.existsByUsername("nonexistent");

        // Assert: Verify that the mock method returns false
        assertThat(exists).isFalse();
    }
}
