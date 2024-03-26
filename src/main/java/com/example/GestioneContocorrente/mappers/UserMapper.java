package com.example.GestioneContocorrente.mappers;

import com.example.GestioneContocorrente.dtos.UserDtoResponse;
import com.example.GestioneContocorrente.model.User;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
@Component
public class UserMapper implements Mapper<User,UserDtoResponse> {
    public UserDtoResponse map(User user) {
        return new UserDtoResponse(user.getId(),user.getFirstname(),user.getLastname(),user.getSsn(),user.getPassword(),user.getCreatedAt());
    }
}
