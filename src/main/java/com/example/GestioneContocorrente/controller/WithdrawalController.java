package com.example.GestioneContocorrente.controller;

import com.example.GestioneContocorrente.dtos.BankAccountDtoResponse;
import com.example.GestioneContocorrente.dtos.WithdrawalDtoResponse;
import com.example.GestioneContocorrente.model.Withdrawal;
import com.example.GestioneContocorrente.service.WithdrawalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/withdrawal")

@RestController
public class WithdrawalController {
    private final WithdrawalService withdrawalService;


    public WithdrawalController(WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }


    @GetMapping
    public ResponseEntity<List<WithdrawalDtoResponse>> getAllAccounts(){
        List<WithdrawalDtoResponse> withdrawalListDTO = withdrawalService.getAllWithdrawals();
        return ResponseEntity.status(HttpStatus.OK).body(withdrawalListDTO);
    }
}
