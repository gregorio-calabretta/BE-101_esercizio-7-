package com.example.GestioneContocorrente.controller;

import com.example.GestioneContocorrente.dtos.BankAccountDtoRequest;
import com.example.GestioneContocorrente.dtos.BankAccountDtoResponse;
import com.example.GestioneContocorrente.dtos.DepositDtoRequest;
import com.example.GestioneContocorrente.dtos.DepositDtoResponse;
import com.example.GestioneContocorrente.exception.ResourceNotFoundException;
import com.example.GestioneContocorrente.model.Deposit;
import com.example.GestioneContocorrente.service.DepositService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/deposits")

@RestController
public class DepositController {
    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @GetMapping
    public ResponseEntity<List<DepositDtoResponse>> getAllDeposits(){
        List<DepositDtoResponse> depositListDTO = depositService.getAlldeposits();
        return ResponseEntity.status(HttpStatus.OK).body(depositListDTO);
    }
    @PostMapping
    public ResponseEntity<DepositDtoResponse> createDeposit(@RequestBody DepositDtoRequest deposit) throws ResourceNotFoundException {
        DepositDtoResponse responseDTO  = depositService.createDeposit(deposit);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    @GetMapping(path = "{depositUuid}")
    public ResponseEntity<DepositDtoResponse> getDepositById(@PathVariable("depositUuid") UUID id) throws ResourceNotFoundException {
        DepositDtoResponse responseDTO = depositService.getDepositById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @DeleteMapping(path = "{depositUuid}")
    public void deleteBankAccountById(@PathVariable("depositUuid") UUID id){
        depositService.deleteDeposit(id);
    }
}
