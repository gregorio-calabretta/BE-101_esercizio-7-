package com.example.GestioneContocorrente.service;

import com.example.GestioneContocorrente.dtos.UserDtoRequest;
import com.example.GestioneContocorrente.dtos.UserDtoResponse;
import com.example.GestioneContocorrente.dtos.WithdrawalDtoRequest;
import com.example.GestioneContocorrente.dtos.WithdrawalDtoResponse;
import com.example.GestioneContocorrente.exception.ResourceNotFoundException;
import com.example.GestioneContocorrente.exception.WithdrawalExceedFundsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface WithdrawalService {
    List<WithdrawalDtoResponse> getAllWithdrawals();
    WithdrawalDtoResponse getWithdrawalById(UUID id) throws Exception;
    void deleteWithdrawal(UUID id);

    WithdrawalDtoResponse createWithdrawal(WithdrawalDtoRequest withdrawal) throws ResourceNotFoundException, WithdrawalExceedFundsException;
}
