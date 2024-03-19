package com.example.GestioneContocorrente.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

import java.sql.Timestamp;
import java.util.UUID;

public class UserDtoRequest {
    private UUID user_id;
    private String firstname;
    private String lastname;

    private String username;


    public UserDtoRequest(@JsonProperty(value = "user_id",required = true) UUID user_id,
                          @JsonProperty(value = "firstname",required = true) String firstname,
                          @JsonProperty(value = "lastname",required = true) String lastname,
                          @JsonProperty(value = "username",required = true) String username) {
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
    }


}
