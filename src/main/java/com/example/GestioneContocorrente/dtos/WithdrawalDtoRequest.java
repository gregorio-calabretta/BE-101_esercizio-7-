package com.example.GestioneContocorrente.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.UUID;

public class WithdrawalDtoRequest {
    private final Long amount;
    private final Timestamp date;




    public WithdrawalDtoRequest(@JsonProperty(value = "amount",required = true) Long amount,
                                @JsonProperty(value = "date",required = true) Timestamp date) {
        this.amount = amount;
        this.date = date;
    }
}
