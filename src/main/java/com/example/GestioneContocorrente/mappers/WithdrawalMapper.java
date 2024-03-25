package com.example.GestioneContocorrente.mappers;

import com.example.GestioneContocorrente.dtos.WithdrawalDtoResponse;
import com.example.GestioneContocorrente.model.Withdrawal;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
@Component
public class WithdrawalMapper implements Mapper<Withdrawal,WithdrawalDtoResponse>{
    public WithdrawalDtoResponse map(Withdrawal withdrawal) {
        return new WithdrawalDtoResponse(withdrawal.getId(),withdrawal.getAmount(), withdrawal.getDate());
    }
}
