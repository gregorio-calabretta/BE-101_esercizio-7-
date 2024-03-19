package com.example.GestioneContocorrente.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

import java.sql.Timestamp;
import java.util.UUID;

public class DepositDtoRequest {

    private Long amount;
    private Timestamp date;

    public DepositDtoRequest(@JsonProperty(value = "amount",required = true) Long amount,
                             @JsonProperty(value = "date",required = true) Timestamp date) {
        this.amount = amount;
        this.date = date;
    }
}
