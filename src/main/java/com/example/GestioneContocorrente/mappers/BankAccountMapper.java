package com.example.GestioneContocorrente.mappers;

import com.example.GestioneContocorrente.dtos.BankAccountDtoResponse;
import com.example.GestioneContocorrente.model.BankAccount;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class BankAccountMapper implements Mapper<BankAccount, BankAccountDtoResponse> {
    public BankAccountDtoResponse map(BankAccount bankAccount) {
        return new BankAccountDtoResponse(bankAccount.getId(),bankAccount.getBalance(), bankAccount.getCreatedAt());
    }
}
