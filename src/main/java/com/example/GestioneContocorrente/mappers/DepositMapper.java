package com.example.GestioneContocorrente.mappers;

import com.example.GestioneContocorrente.dtos.DepositDtoResponse;
import com.example.GestioneContocorrente.model.Deposit;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
@Component
public class DepositMapper implements Mapper<Deposit,DepositDtoResponse>{
    public DepositDtoResponse map(Deposit deposit) {
        return new DepositDtoResponse(deposit.getId(),deposit.getAmount(), deposit.getDate());
    }
}
