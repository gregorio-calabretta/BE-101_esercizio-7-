package com.example.GestioneContocorrente.controller;

import com.example.GestioneContocorrente.dtos.BankAccountDtoResponse;
import com.example.GestioneContocorrente.service.BankAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/bankaccount")
@RestController
public class BankAccountController {
    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public ResponseEntity<List<BankAccountDtoResponse>> getAllAccounts(){
        List<BankAccountDtoResponse> accountListDTO = bankAccountService.getAllAccounts();
        return ResponseEntity.status(HttpStatus.OK).body(accountListDTO);
    }
}
