package com.example.GestioneContocorrente.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class UserDtoRequest {

    @JsonProperty(value = "firstname")
    private final String firstname;
    @JsonProperty(value = "lastname")
    private final String lastname;
    @JsonProperty(value = "ssn")
    private final String ssn;
    @JsonProperty(value = "username")
    private final String username;
    @JsonProperty(value = "password")
    private final String password;

}
