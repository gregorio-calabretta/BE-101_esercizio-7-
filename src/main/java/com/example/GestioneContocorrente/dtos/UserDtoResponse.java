package com.example.GestioneContocorrente.dtos;

import lombok.Data;


import java.time.LocalDateTime;

@Data
public class UserDtoResponse {
    private final Long id;
    private final String firstname;
    private final String lastname;
    private final String ssn;
    private final String username;
    private final LocalDateTime createdAt;
}
