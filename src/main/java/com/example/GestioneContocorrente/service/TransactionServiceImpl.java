package com.example.GestioneContocorrente.service;

import com.example.GestioneContocorrente.dtos.TransactionDto;
import com.example.GestioneContocorrente.repository.DepositRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class TransactionServiceImpl implements TransactionService{
    private final DepositRepo depositRepo;

    public TransactionServiceImpl(DepositRepo depositRepo) {
        this.depositRepo = depositRepo;
    }

    @Override
    public List<TransactionDto> getLast5Transactions(UUID userId,UUID bankAccountId) {
        return depositRepo.getLast5TransactionsByUserIdAndBankAccountId(userId,bankAccountId);
    }
}
