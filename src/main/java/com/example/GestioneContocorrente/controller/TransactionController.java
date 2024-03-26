package com.example.GestioneContocorrente.controller;

import com.example.GestioneContocorrente.dtos.TransactionDto;
import com.example.GestioneContocorrente.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/api/transactions")

@RestController
public class TransactionController {
    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @GetMapping(path = "users/{userLong}/bankaccounts/{bankAccountLong}")
public ResponseEntity<List<TransactionDto>> getLast5Transactions(@PathVariable("userLong") Long userId,@PathVariable("bankAccountLong") Long bankAccountId) throws Exception {
        List<TransactionDto> responseDTO = transactionService.getLast5Transactions(userId,bankAccountId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
