package com.example.GestioneContocorrente.controller;

import com.example.GestioneContocorrente.dtos.BankAccountDtoRequest;
import com.example.GestioneContocorrente.dtos.BankAccountDtoResponse;
import com.example.GestioneContocorrente.model.BankAccount;
import com.example.GestioneContocorrente.service.BankAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/bankaccounts")
@RestController
public class BankAccountController {
    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public ResponseEntity<List<BankAccountDtoResponse>> getAllBankAccounts(){
        List<BankAccountDtoResponse> accountListDTO = bankAccountService.getAllBankAccounts();
        return ResponseEntity.status(HttpStatus.OK).body(accountListDTO);
    }

    @PostMapping
    public ResponseEntity<BankAccountDtoResponse> createBankAccount(@RequestBody BankAccountDtoRequest bankAccount) throws Exception {
        BankAccountDtoResponse responseDTO  = bankAccountService.createBankAccount(bankAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    @GetMapping(path = "{bankAccountUuid}")
    public ResponseEntity<BankAccountDtoResponse> getBankAccountById(@PathVariable("bankAccountUuid") UUID id) throws Exception {
        BankAccountDtoResponse responseDTO = bankAccountService.getBankAccountById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @DeleteMapping(path = "{bankAccountUuid}")
    public void deleteBankAccountById(@PathVariable("bankAccountUuid") UUID id){
        bankAccountService.deleteBankAccount(id);
    }
}
