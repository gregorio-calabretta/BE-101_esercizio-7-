package com.example.GestioneContocorrente.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;



@Data
public class WithdrawalDtoRequest {
    @JsonProperty
    private final Long amount;
    @JsonProperty(value = "user_id",required = true)
    private final Long userId;
    @JsonProperty(value = "bank_account_id",required = true)
    private final Long bankAccountId;
}
