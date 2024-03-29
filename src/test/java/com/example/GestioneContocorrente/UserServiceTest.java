package com.example.GestioneContocorrente;

import com.example.GestioneContocorrente.dtos.UserDtoRequest;
import com.example.GestioneContocorrente.dtos.UserDtoResponse;
import com.example.GestioneContocorrente.exception.ResourceNotFoundException;
import com.example.GestioneContocorrente.model.User;
import com.example.GestioneContocorrente.repository.UserRepo;
import com.example.GestioneContocorrente.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;


    @Test
    @DisplayName("Test findById Success")
    void testFindById() throws Exception {
        User user = User.builder().id(1L).firstname("firstname").lastname("lastname").
                ssn("ssn").createdAt(LocalDateTime.now()).
                username("username").password("password").build();
//doReturn(VALUE_TO_RETURN).when(MOCK_CLASS_INSTANCE).MOCK_METHOD
        doReturn(Optional.of(user)).when(userRepo).getUserById(1L);

        UserDtoResponse expectedDTO = new UserDtoResponse(user.getId(),
                        user.getFirstname(),user.getLastname(),user.getSsn(),
                        user.getUsername(),user.getCreatedAt());

        // Execute the service call
        UserDtoResponse returnedUser = userService.getUserById(1L);

        // Assert the response

        assertNotNull(returnedUser);
        assertEquals(expectedDTO, returnedUser);
    }

    @Test
    @DisplayName("Test findById Not Found")
    void testFindByIdNotFound()   {
        // Setup our mock repository
        doReturn(Optional.empty()).when(userRepo).findById(1L);
        //asserts that it throws a ResourceNotFoundException. If the exception is not thrown, the test fails.
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(1L),
                "Expected ResourceNotFoundException when user is not found");

        // Verify exception message
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    @DisplayName("Test findAll")
    void testFindAll() {
        // Setup our mock repository
        User user = User.builder().id(1L).firstname("firstname").lastname("lastname").
                ssn("ssn").createdAt(LocalDateTime.now()).
                username("username").password("password").build();
        User user2 = User.builder().id(2L).firstname("firstname2").lastname("lastname2").
                ssn("ssn2").createdAt(LocalDateTime.now()).
                username("username2").password("password2").build();
        doReturn(Arrays.asList(user, user2)).when(userRepo).findAll();

        // Execute the service call
        List<UserDtoResponse> users = userService.getAllUsers();

        // Assert the response
        Assertions.assertEquals(2, users.size(), "findAll should return 2 users");
    }

    @Test
    @DisplayName("Test save user")
    void testSave() {
        // Set up our mock repository
        User user = User.builder().id(1L).firstname("firstname").lastname("lastname").
                ssn("ssn").createdAt(LocalDateTime.now()).
                username("username").password("password").build();
        doReturn(user).when(userRepo).save(any());

        UserDtoRequest dtoRequest = new UserDtoRequest(
                user.getFirstname(),user.getLastname(),user.getSsn(),
                user.getUsername(),user.getPassword());

        // Execute the service call
        UserDtoResponse returnedUser= userService.createUser(dtoRequest);

        // Assert the response
        assertNotNull(returnedUser, "The saved user should not be null");
    }
}
