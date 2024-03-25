package com.example.GestioneContocorrente.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
public class WithdrawalDtoResponse {
    private final UUID id;
    private final Long amount;
    private final LocalDateTime date;
}
