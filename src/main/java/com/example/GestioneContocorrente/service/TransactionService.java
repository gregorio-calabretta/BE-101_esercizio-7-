package com.example.GestioneContocorrente.service;

import com.example.GestioneContocorrente.dtos.TransactionDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface TransactionService {
    List<TransactionDto> getLast5Transactions(Long userId,Long bankAccountId);

}
