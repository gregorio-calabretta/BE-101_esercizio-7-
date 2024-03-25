package com.example.GestioneContocorrente.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data

public class TransactionDto {
    private final UUID bank_account_id;
    private final UUID user_id;
    private final long amount;
    private final LocalDateTime date;
}
