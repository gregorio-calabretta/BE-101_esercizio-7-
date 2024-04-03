package com.example.GestioneContocorrente.service;

import com.example.GestioneContocorrente.dtos.BankAccountDtoRequest;
import com.example.GestioneContocorrente.dtos.BankAccountDtoResponse;
import com.example.GestioneContocorrente.exception.InvalidInputException;
import com.example.GestioneContocorrente.exception.ResourceNotFoundException;
import com.example.GestioneContocorrente.mappers.Mapper;
import com.example.GestioneContocorrente.model.BankAccount;
import com.example.GestioneContocorrente.model.User;
import com.example.GestioneContocorrente.repository.BankAccountRepo;
import com.example.GestioneContocorrente.repository.UserRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class BankAccountServiceImpl implements BankAccountService{
    private final BankAccountRepo bankAccountRepo;
    private final UserRepo userRepo;
    private final Mapper<BankAccount, BankAccountDtoResponse> mapper;

    public BankAccountServiceImpl(BankAccountRepo bankAccountRepo, UserRepo userRepo, @Qualifier("bankAccountMapper") Mapper<BankAccount, BankAccountDtoResponse> mapper) {
        this.bankAccountRepo = bankAccountRepo;
        this.userRepo = userRepo;
        this.mapper = mapper;
    }

    @Override
    public List<BankAccountDtoResponse> getAllBankAccounts() {
        List<BankAccount> bankAccountsList = bankAccountRepo.findAll();
        return mapper.mapAll(bankAccountsList);
    }

    @Override
    public BankAccountDtoResponse createBankAccount(BankAccountDtoRequest bankAccountDtoRequest) throws Exception {
        User user = userRepo.findById(bankAccountDtoRequest.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        BankAccount bankAccount = BankAccount.builder()
                .balance(bankAccountDtoRequest.getBalance())
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();
        bankAccountRepo.save(bankAccount);
        return mapper.map(bankAccount);
    }

    @Override
    public BankAccountDtoResponse getBankAccountById(Long id) throws Exception {
        if(id == null ) {
        throw new InvalidInputException("id can't be null");
        }
        BankAccount bankAccount = bankAccountRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bank account not found"));
        return mapper.map(bankAccount);
    }

    @Override
    public void deleteBankAccount(Long id) {
        bankAccountRepo.deleteById(id);
    }


}
