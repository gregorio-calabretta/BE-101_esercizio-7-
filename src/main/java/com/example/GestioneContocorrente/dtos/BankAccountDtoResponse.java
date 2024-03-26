package com.example.GestioneContocorrente.dtos;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BankAccountDtoResponse {
    private final Long id;
    private final long balance;
    private final LocalDateTime createdAt;
}
