package com.example.GestioneContocorrente.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BankAccountDtoRequest {
    @JsonProperty(defaultValue = "0")
    private final long balance;
    @JsonProperty
    private final LocalDateTime createdAt;
    @JsonProperty(value = "user_id",required = true)
    private final UUID userId;
}
