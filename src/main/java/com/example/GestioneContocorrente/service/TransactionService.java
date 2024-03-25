package com.example.GestioneContocorrente.service;

import com.example.GestioneContocorrente.dtos.TransactionDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface TransactionService {
    List<TransactionDto> getLast5Transactions(UUID userId,UUID bankAccountId);

}
