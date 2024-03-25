package com.example.GestioneContocorrente.dtos;


import jakarta.annotation.Nullable;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
public class BankAccountDtoResponse {
    private final UUID id;
    private final long balance;
    private final LocalDateTime createdAt;
}
