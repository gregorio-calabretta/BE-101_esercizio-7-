package com.example.GestioneContocorrente.controller;

import com.example.GestioneContocorrente.dtos.BankAccountDtoResponse;
import com.example.GestioneContocorrente.dtos.UserDtoResponse;
import com.example.GestioneContocorrente.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/user")

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDtoResponse>> getAllAccounts(){
        List<UserDtoResponse> userListDTO = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userListDTO);
    }
}
