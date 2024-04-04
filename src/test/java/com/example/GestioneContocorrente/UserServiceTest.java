package com.example.GestioneContocorrente;

import com.example.GestioneContocorrente.dtos.UserDtoRequest;
import com.example.GestioneContocorrente.dtos.UserDtoResponse;
import com.example.GestioneContocorrente.exception.ResourceNotFoundException;
import com.example.GestioneContocorrente.mappers.UserMapper;
import com.example.GestioneContocorrente.model.User;
import com.example.GestioneContocorrente.repository.UserRepo;
import com.example.GestioneContocorrente.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class UserServiceTest {


    @Mock
    private UserRepo userRepo;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    User user = User.builder().id(1L).firstname("firstname").lastname("lastname").
            ssn("ssn").createdAt(LocalDateTime.now()).
            username("username").password("password").build();

    User user2 = User.builder().id(1L).firstname("Gigi").lastname("Rossi").
            ssn("jojnasd3").createdAt(LocalDateTime.now()).
            username("jkjkd").password("jciisk354wja").build();


    UserDtoResponse userDtoResponse = new UserDtoResponse(user.getId(),
            user.getFirstname(), user.getLastname(), user.getSsn(),
            user.getUsername(), user.getCreatedAt());
    UserDtoResponse userDtoResponse2 = new UserDtoResponse(user2.getId(),
            user2.getFirstname(), user2.getLastname(), user2.getSsn(),
            user2.getUsername(), user2.getCreatedAt());

    UserDtoRequest userDtoRequest = new UserDtoRequest(user.getFirstname(),
            user.getLastname(),user.getSsn(),user.getUsername(),user.getPassword());


    @Test
    public void givenLongId_whenFindById_thenReturnUser() throws Exception {

        when(userRepo.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.map(user)).thenReturn(userDtoResponse);

        UserDtoResponse response = userService.getUserById(1L);

        assertNotNull(response);
        assertEquals(response,userDtoResponse);
    }

    @Test
    public void givenLongId_whenFindByUser_thenThrowResourceNotFoundException()   {
        doReturn(Optional.empty()).when(userRepo).findById(1L);
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(1L),
                "Expected ResourceNotFoundException when user is not found");

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    public void whenFindAll_thenReturnAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);

        List<UserDtoResponse> userDtoResponseList = new ArrayList<>();
        userDtoResponseList.add(userDtoResponse);
        userDtoResponseList.add(userDtoResponse2);

        when(userRepo.findAll()).thenReturn((userList));

        when(userMapper.mapAll(userList))
                .thenReturn(userDtoResponseList);


        List<UserDtoResponse> response = userService.getAllUsers();

        Assertions.assertEquals(2, response.size(), "findAll should return 2 users");
    }

    @Test
    public void whenSave_thenSaveAndReturnUser() {

        when(userRepo.save(user)).thenReturn(user);

        when(userMapper.map(user)).thenReturn(userDtoResponse);

        UserDtoResponse response = userService.createUser(userDtoRequest);

        assertEquals(response,userDtoResponse);
    }

    @Test
    public void testDelete() {
        userService.deleteUser(user.getId());
        verify(userRepo, times(1)).deleteById(user.getId());
    }


}
