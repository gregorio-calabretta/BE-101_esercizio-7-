package com.example.GestioneContocorrente.service;

import com.example.GestioneContocorrente.dtos.BankAccountDtoRequest;
import com.example.GestioneContocorrente.dtos.BankAccountDtoResponse;
import com.example.GestioneContocorrente.dtos.UserDtoRequest;
import com.example.GestioneContocorrente.dtos.UserDtoResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserService {
     List<UserDtoResponse> getAllUsers();
     UserDtoResponse createUser(UserDtoRequest userDtoRequest);
     UserDtoResponse getUserById(UUID id) throws Exception;
     void deleteUser(UUID id);

}
