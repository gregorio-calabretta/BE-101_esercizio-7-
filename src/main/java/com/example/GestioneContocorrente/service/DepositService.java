package com.example.GestioneContocorrente.service;

import com.example.GestioneContocorrente.dtos.*;
import com.example.GestioneContocorrente.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface DepositService {
     List<DepositDtoResponse> getAlldeposits();
     DepositDtoResponse getDepositById(Long id) throws ResourceNotFoundException;
     void deleteDeposit(Long id);

     DepositDtoResponse createDeposit(DepositDtoRequest depositDtoRequest) throws ResourceNotFoundException;

}
