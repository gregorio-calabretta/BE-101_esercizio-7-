package com.example.GestioneContocorrente.repository;

import com.example.GestioneContocorrente.dtos.TransactionDto;
import com.example.GestioneContocorrente.exception.ResourceNotFoundException;

import java.util.List;

public interface TransactionRepo {

    List<TransactionDto> findLast5TransactionsByUserIdAndBankAccountId(Long userId, Long bankAccountId) throws ResourceNotFoundException;
}
