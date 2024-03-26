package com.example.GestioneContocorrente.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WithdrawalDtoResponse {
    private final Long id;
    private final Long amount;
    private final LocalDateTime date;
}
