package com.example.GestioneContocorrente.service;

import com.example.GestioneContocorrente.dtos.BankAccountDtoResponse;
import com.example.GestioneContocorrente.dtos.DepositDtoRequest;
import com.example.GestioneContocorrente.dtos.DepositDtoResponse;
import com.example.GestioneContocorrente.exception.ResourceNotFoundException;
import com.example.GestioneContocorrente.mappers.DepositMapper;
import com.example.GestioneContocorrente.mappers.Mapper;
import com.example.GestioneContocorrente.model.BankAccount;
import com.example.GestioneContocorrente.model.Deposit;
import com.example.GestioneContocorrente.model.User;
import com.example.GestioneContocorrente.model.Withdrawal;
import com.example.GestioneContocorrente.repository.BankAccountRepo;
import com.example.GestioneContocorrente.repository.DepositRepo;
import com.example.GestioneContocorrente.repository.UserRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Service
public class DepositServiceImpl implements DepositService{
    private final DepositRepo depositRepo;
    private final UserRepo userRepo;
    private final BankAccountRepo bankAccountRepo;
    private final Mapper<Deposit, DepositDtoResponse> mapper;

    public DepositServiceImpl(DepositRepo depositRepo, UserRepo userRepo, BankAccountRepo bankAccountRepo, @Qualifier("depositMapper") Mapper<Deposit, DepositDtoResponse> mapper) {
        this.depositRepo = depositRepo;
        this.userRepo = userRepo;
        this.bankAccountRepo = bankAccountRepo;
        this.mapper = mapper;
    }

    @Override
    public List<DepositDtoResponse> getAlldeposits() {
        List<Deposit> depositList = depositRepo.findAll();
        return mapper.mapAll(depositList);
    }

    @Override
    public DepositDtoResponse getDepositById(UUID id) throws ResourceNotFoundException {
        Deposit deposit = depositRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Deposit not found"));
        return mapper.map(deposit);
    }

    @Override
    public void deleteDeposit(UUID id) {
        depositRepo.deleteById(id);
    }

    @Override
    public DepositDtoResponse createDeposit(DepositDtoRequest depositDtoRequest) throws ResourceNotFoundException {
        User user = userRepo.findById(depositDtoRequest.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        BankAccount bankAccount = bankAccountRepo.findById(depositDtoRequest.getBankAccountId()).orElseThrow(() -> new ResourceNotFoundException("BankAccount not found"));
        Deposit deposit = Deposit.builder()
                .amount(depositDtoRequest.getAmount())
                .date(LocalDateTime.now())
                .bankAccount(bankAccount)
                .user(user)
                .build();
        bankAccount.setBalance(bankAccount.getBalance() + deposit.getAmount());
        depositRepo.save(deposit);
        bankAccountRepo.save(bankAccount);
        return mapper.map(deposit);
    }
}
