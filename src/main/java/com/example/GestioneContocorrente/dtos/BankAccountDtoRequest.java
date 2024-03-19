package com.example.GestioneContocorrente.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;


public class BankAccountDtoRequest {
    private final long balance;
    private final Timestamp created_at;

    public BankAccountDtoRequest(@JsonProperty(value = "balance",required = true) long balance,
                                 @JsonProperty(value = "created_at",required = true) Timestamp created_at) {
        this.balance = balance;
        this.created_at = created_at;
    }

}
