package com.example.GestioneContocorrente.service;

import com.example.GestioneContocorrente.dtos.*;
import com.example.GestioneContocorrente.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface DepositService {
     List<DepositDtoResponse> getAlldeposits();
     DepositDtoResponse getDepositById(UUID id) throws ResourceNotFoundException;
     void deleteDeposit(UUID id);

     DepositDtoResponse createDeposit(DepositDtoRequest depositDtoRequest) throws ResourceNotFoundException;

}
