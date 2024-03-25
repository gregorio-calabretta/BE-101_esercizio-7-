package com.example.GestioneContocorrente.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class WithdrawalDtoRequest {
    @JsonProperty
    private final Long amount;
    @JsonProperty(value = "user_id",required = true)
    private final UUID userId;
    @JsonProperty(value = "bank_account_id",required = true)
    private final UUID bankAccountId;
}
