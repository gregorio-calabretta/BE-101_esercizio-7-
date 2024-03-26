package com.example.GestioneContocorrente.controller;

import com.example.GestioneContocorrente.dtos.BankAccountDtoRequest;
import com.example.GestioneContocorrente.dtos.BankAccountDtoResponse;
import com.example.GestioneContocorrente.service.BankAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping(path = "{bankAccountLong}")
    public ResponseEntity<BankAccountDtoResponse> getBankAccountById(@PathVariable("bankAccountLong") Long id) throws Exception {
        BankAccountDtoResponse responseDTO = bankAccountService.getBankAccountById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @DeleteMapping(path = "{bankAccountLong}")
    public void deleteBankAccountById(@PathVariable("bankAccountLong") Long id){
        bankAccountService.deleteBankAccount(id);
    }
}
