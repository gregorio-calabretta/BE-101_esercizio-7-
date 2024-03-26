package com.example.GestioneContocorrente.service;

import com.example.GestioneContocorrente.dtos.BankAccountDtoRequest;
import com.example.GestioneContocorrente.dtos.BankAccountDtoResponse;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface BankAccountService {
    List<BankAccountDtoResponse> getAllBankAccounts();
    BankAccountDtoResponse createBankAccount(BankAccountDtoRequest bankAccountDtoRequest) throws Exception;
    BankAccountDtoResponse getBankAccountById(Long id) throws Exception;
    void deleteBankAccount(Long id);



}
