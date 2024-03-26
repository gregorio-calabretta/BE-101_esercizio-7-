package com.example.GestioneContocorrente.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data

public class TransactionDto {
    private final Long bank_account_id;
    private final Long user_id;
    private final long amount;
    private final Date date;
}
