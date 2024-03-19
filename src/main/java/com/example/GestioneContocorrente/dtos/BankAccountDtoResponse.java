package com.example.GestioneContocorrente.dtos;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class BankAccountDtoResponse {
    private final UUID bank_account_id;
    private final long balance;
    private final Timestamp created_at;

    public BankAccountDtoResponse(UUID bank_account_id, long balance, Timestamp created_at) {
        this.bank_account_id = bank_account_id;
        this.balance = balance;
        this.created_at = created_at;
    }
}
