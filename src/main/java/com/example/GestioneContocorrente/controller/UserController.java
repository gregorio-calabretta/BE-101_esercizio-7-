package com.example.GestioneContocorrente.controller;

import com.example.GestioneContocorrente.dtos.*;
import com.example.GestioneContocorrente.service.BankAccountService;
import com.example.GestioneContocorrente.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/users")

@RestController
public class UserController {
    private final UserService userService;
    private final BankAccountService bankAccountService;

    public UserController(UserService userService, BankAccountService bankAccountService) {
        this.userService = userService;
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public ResponseEntity<List<UserDtoResponse>> getAllUsers(){
        List<UserDtoResponse> userListDTO = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userListDTO);
    }
    @PostMapping
    public ResponseEntity<UserDtoResponse> createUser(@RequestBody UserDtoRequest user) throws Exception {
        UserDtoResponse responseDTO  = userService.createUser(user);
        bankAccountService.createBankAccount(new BankAccountDtoRequest(0, LocalDateTime.now(),responseDTO.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    @GetMapping(path = "{userUuid}")
    public ResponseEntity<UserDtoResponse> getUserById(@PathVariable("userUuid") UUID id) throws Exception {
        UserDtoResponse responseDTO = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
    @DeleteMapping(path = "{userUuid}")
    public void deleteUserById(@PathVariable("userUuid") UUID id){
        userService.deleteUser(id);
    }



}
