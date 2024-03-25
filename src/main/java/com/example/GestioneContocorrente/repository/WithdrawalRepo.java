package com.example.GestioneContocorrente.repository;

import com.example.GestioneContocorrente.model.Deposit;
import com.example.GestioneContocorrente.model.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface WithdrawalRepo extends JpaRepository<Withdrawal, UUID> {
    List<Withdrawal> findAll();
    Withdrawal save(Withdrawal withdrawal);

}
