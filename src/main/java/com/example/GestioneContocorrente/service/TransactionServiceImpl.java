package com.example.GestioneContocorrente.service;

import com.example.GestioneContocorrente.dtos.TransactionDto;
import com.example.GestioneContocorrente.exception.ResourceNotFoundException;
import com.example.GestioneContocorrente.repository.DepositRepo;
import com.example.GestioneContocorrente.repository.TransactionRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepo transactionRepo;

    public TransactionServiceImpl(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }


    @Override
    public List<TransactionDto> getLast5Transactions(Long userId,Long bankAccountId) throws ResourceNotFoundException {
        return transactionRepo.findLast5TransactionsByUserIdAndBankAccountId(userId,bankAccountId);
    }
}
