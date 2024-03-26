package com.example.GestioneContocorrente.repository;

import com.example.GestioneContocorrente.dtos.TransactionDto;

import java.util.List;

public interface TransactionRepo {

    List<TransactionDto> findLast5TransactionsByUserIdAndBankAccountId(Long userId, Long bankAccountId);
}
