package com.example.GestioneContocorrente.controller;

import com.example.GestioneContocorrente.dtos.DepositDtoRequest;
import com.example.GestioneContocorrente.dtos.DepositDtoResponse;
import com.example.GestioneContocorrente.dtos.WithdrawalDtoRequest;
import com.example.GestioneContocorrente.dtos.WithdrawalDtoResponse;
import com.example.GestioneContocorrente.exception.ResourceNotFoundException;
import com.example.GestioneContocorrente.exception.WithdrawalExceedFundsException;
import com.example.GestioneContocorrente.service.WithdrawalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/withdrawals")

@RestController
public class WithdrawalController {
    private final WithdrawalService withdrawalService;

    public WithdrawalController(WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    @GetMapping
    public ResponseEntity<List<WithdrawalDtoResponse>> getAllWithdrawals(){
        List<WithdrawalDtoResponse> withdrawalListDTO = withdrawalService.getAllWithdrawals();
        return ResponseEntity.status(HttpStatus.OK).body(withdrawalListDTO);
    }
    @PostMapping
    public ResponseEntity<WithdrawalDtoResponse> createWithdrawal(@RequestBody WithdrawalDtoRequest withdrawal) throws WithdrawalExceedFundsException, ResourceNotFoundException {
        WithdrawalDtoResponse responseDTO  = withdrawalService.createWithdrawal(withdrawal);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    @GetMapping(path = "{withdrawalUuid}")
    public ResponseEntity<WithdrawalDtoResponse> getWithdrawalById(@PathVariable("withdrawalUuid") UUID id) throws Exception {
        WithdrawalDtoResponse responseDTO = withdrawalService.getWithdrawalById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @DeleteMapping(path = "{withdrawalUuid}")
    public void deleteWithdrawalById(@PathVariable("withdrawalUuid") UUID id){
        withdrawalService.deleteWithdrawal(id);
    }
}
