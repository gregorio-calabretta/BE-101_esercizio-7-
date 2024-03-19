package com.example.GestioneContocorrente.dtos;

import java.sql.Timestamp;
import java.util.UUID;

public class UserDtoResponse {
    private UUID user_id;
    private String firstname;
    private String lastname;
    private String ssn;
    private String username;

    private Timestamp created_at;

    public UserDtoResponse(UUID user_id, String firstname, String lastname, String ssn, String username, Timestamp created_at) {
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
        this.username = username;
        this.created_at = created_at;
    }
}
