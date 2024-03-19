package com.example.GestioneContocorrente.dtos;

import java.sql.Timestamp;
import java.util.UUID;

public class WithdrawalDtoResponse {
    private UUID withdrawal_id;
    private Long amount;
    private Timestamp date;

    public WithdrawalDtoResponse(UUID withdrawal_id, Long amount, Timestamp date) {
        this.withdrawal_id = withdrawal_id;
        this.amount = amount;
        this.date = date;
    }

}
