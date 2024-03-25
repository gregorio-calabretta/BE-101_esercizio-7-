package com.example.GestioneContocorrente.service;

import com.example.GestioneContocorrente.dtos.BankAccountDtoRequest;
import com.example.GestioneContocorrente.dtos.BankAccountDtoResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface BankAccountService {
    List<BankAccountDtoResponse> getAllBankAccounts();
    BankAccountDtoResponse createBankAccount(BankAccountDtoRequest bankAccountDtoRequest) throws Exception;
    BankAccountDtoResponse getBankAccountById(UUID id) throws Exception;
    void deleteBankAccount(UUID id);



}
