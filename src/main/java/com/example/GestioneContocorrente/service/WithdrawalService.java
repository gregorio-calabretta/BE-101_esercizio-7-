package com.example.GestioneContocorrente.service;

import com.example.GestioneContocorrente.dtos.UserDtoRequest;
import com.example.GestioneContocorrente.dtos.UserDtoResponse;
import com.example.GestioneContocorrente.dtos.WithdrawalDtoRequest;
import com.example.GestioneContocorrente.dtos.WithdrawalDtoResponse;
import com.example.GestioneContocorrente.exception.ResourceNotFoundException;
import com.example.GestioneContocorrente.exception.WithdrawalExceedFundsException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface WithdrawalService {
    List<WithdrawalDtoResponse> getAllWithdrawals();
    WithdrawalDtoResponse getWithdrawalById(Long id) throws Exception;
    void deleteWithdrawal(Long id);

    WithdrawalDtoResponse createWithdrawal(WithdrawalDtoRequest withdrawal) throws ResourceNotFoundException, WithdrawalExceedFundsException;
}
