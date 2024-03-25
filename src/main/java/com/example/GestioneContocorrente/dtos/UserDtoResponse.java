package com.example.GestioneContocorrente.dtos;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
public class UserDtoResponse {
    private final UUID id;
    private final String firstname;
    private final String lastname;
    private final String ssn;
    private final String username;
    private final LocalDateTime createdAt;
}
