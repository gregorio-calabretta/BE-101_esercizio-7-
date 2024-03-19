package com.example.GestioneContocorrente.dtos;

import jakarta.persistence.Column;

import java.sql.Timestamp;
import java.util.UUID;

public class DepositDtoResponse {
    private UUID deposit_id;
    private Long amount;
    private Timestamp date;

    public DepositDtoResponse(UUID deposit_id, Long amount, Timestamp date) {
        this.deposit_id = deposit_id;
        this.amount = amount;
        this.date = date;
    }
}
