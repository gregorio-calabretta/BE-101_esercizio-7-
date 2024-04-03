package com.example.GestioneContocorrente.service;

import com.example.GestioneContocorrente.dtos.WithdrawalDtoRequest;
import com.example.GestioneContocorrente.dtos.WithdrawalDtoResponse;
import com.example.GestioneContocorrente.exception.ResourceNotFoundException;
import com.example.GestioneContocorrente.exception.WithdrawalExceedFundsException;
import com.example.GestioneContocorrente.mappers.Mapper;
import com.example.GestioneContocorrente.model.BankAccount;
import com.example.GestioneContocorrente.model.User;
import com.example.GestioneContocorrente.model.Withdrawal;
import com.example.GestioneContocorrente.repository.BankAccountRepo;
import com.example.GestioneContocorrente.repository.UserRepo;
import com.example.GestioneContocorrente.repository.WithdrawalRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WithdrawalServiceImpl implements WithdrawalService{
    private final Mapper<Withdrawal, WithdrawalDtoResponse> mapper;
    public final WithdrawalRepo withdrawalRepo;
    public final UserRepo userRepo;
    public final BankAccountRepo bankAccountRepo;

    public WithdrawalServiceImpl(@Qualifier("withdrawalMapper")  Mapper<Withdrawal, WithdrawalDtoResponse> mapper, WithdrawalRepo withdrawalRepo, UserRepo userRepo, BankAccountRepo bankAccountRepo) {
        this.mapper = mapper;
        this.withdrawalRepo = withdrawalRepo;
        this.userRepo = userRepo;
        this.bankAccountRepo = bankAccountRepo;
    }

    @Override
    public List<WithdrawalDtoResponse> getAllWithdrawals() {
        List<Withdrawal> withdrawalList = withdrawalRepo.findAll();
        return mapper.mapAll(withdrawalList);
    }

    @Override
    public WithdrawalDtoResponse getWithdrawalById(Long id) throws Exception {
        Withdrawal withdrawal = withdrawalRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("withdrawal not found"));
        return mapper.map(withdrawal);
    }

    @Override
    public void deleteWithdrawal(Long id) {
    withdrawalRepo.deleteById(id);
    }

    @Override
    public WithdrawalDtoResponse createWithdrawal(WithdrawalDtoRequest withdrawalDtoRequest) throws ResourceNotFoundException, WithdrawalExceedFundsException {
        User user = userRepo.findById(withdrawalDtoRequest.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        BankAccount bankAccount = bankAccountRepo.findById(withdrawalDtoRequest.getBankAccountId()).orElseThrow(() -> new ResourceNotFoundException("BankAccount not found"));
        if(withdrawalDtoRequest.getAmount()>bankAccount.getBalance()){
            throw new WithdrawalExceedFundsException("You cannot withdraw more than your balance");
        }
        else{
        Withdrawal withdrawal = Withdrawal.builder()
                .amount(withdrawalDtoRequest.getAmount())
                .date(LocalDateTime.now())
                .bankAccount(bankAccount)
                .user(user)
                .build();
        bankAccount.setBalance(bankAccount.getBalance() - withdrawal.getAmount());
        withdrawalRepo.save(withdrawal);
        bankAccountRepo.save(bankAccount);
        return mapper.map(withdrawal);
    }
    }
}
