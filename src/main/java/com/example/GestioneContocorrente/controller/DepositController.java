package com.example.GestioneContocorrente.controller;

import com.example.GestioneContocorrente.dtos.BankAccountDtoResponse;
import com.example.GestioneContocorrente.dtos.DepositDtoResponse;
import com.example.GestioneContocorrente.service.DepositService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/deposit")

@RestController
public class DepositController {
    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @GetMapping
    public ResponseEntity<List<DepositDtoResponse>> getAllAccounts(){
        List<DepositDtoResponse> depositListDTO = depositService.getAllAccounts();
        return ResponseEntity.status(HttpStatus.OK).body(depositListDTO);
    }
}
