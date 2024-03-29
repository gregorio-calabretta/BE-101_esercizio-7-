package com.example.GestioneContocorrente;

import com.example.GestioneContocorrente.dtos.UserDtoResponse;
import com.example.GestioneContocorrente.model.User;
import com.example.GestioneContocorrente.service.UserService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDateTime;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRestControllerTest {
    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /users success")
    void testGetUsersSuccess() throws Exception {
        // Setup our mocked service
        User user = User.builder().firstname("firstname").lastname("lastname").
                ssn("ssn").createdAt(LocalDateTime.now()).
                username("username").password("password").build();

        User user2 = User.builder().firstname("firstname1").lastname("lastname1").
                ssn("ssn1").createdAt(LocalDateTime.now()).
                username("username1").password("password1").build();

        doReturn(Lists.newArrayList(user, user2)).when(userService).getAllUsers();

        // Execute the GET request
        mockMvc.perform(get("/api/users"))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate headers

                // Validate the returned fields
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect( jsonPath("$[0].firstname").value("firstname"))
                .andExpect( jsonPath("$[0].lastname").value("lastname"))
                .andExpect( jsonPath("$[0].ssn").value("ssn"))
                .andExpect( jsonPath("$[0].username").value("username"))
                .andExpect( jsonPath("$[0].password").value("password"))

                .andExpect( jsonPath("$[1].firstname").value("firstname1"))
                .andExpect( jsonPath("$[1].lastname").value("lastname1"))
                .andExpect( jsonPath("$[1].ssn").value("ssn1"))
                .andExpect( jsonPath("$[1].username").value("username1"))
                .andExpect( jsonPath("$[1].password").value("password1"));
    }


    @Test
    @DisplayName("GET /api/users")
    void testGetUsersById() throws Exception {
        // Setup our mocked service
        User user = User.builder().id(1L).firstname("firstname").lastname("lastname").
                ssn("ssn").createdAt(LocalDateTime.now()).
                username("username").build();

         UserDtoResponse userDtoResponse = new UserDtoResponse(user.getId(),user.getFirstname(),user.getLastname()
         ,user.getSsn(),user.getUsername(),user.getCreatedAt());

        doReturn(userDtoResponse).when(userService).getUserById(1L);

        // Execute the GET request
        mockMvc.perform(get("/api/users/{id}", 1L))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))


                .andExpect( jsonPath("$.id").value("1"))
                .andExpect( jsonPath("$.firstname").value("firstname"))
                .andExpect( jsonPath("$.lastname").value("lastname"))
                .andExpect( jsonPath("$.ssn").value("ssn"))
                .andExpect( jsonPath("$.username").value("username"));
    }

}
