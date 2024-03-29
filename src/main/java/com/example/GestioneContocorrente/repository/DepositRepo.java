package com.example.GestioneContocorrente.repository;

import com.example.GestioneContocorrente.dtos.TransactionDto;
import com.example.GestioneContocorrente.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositRepo extends JpaRepository<Deposit, Long> {
    List<Deposit> findAll();
    Deposit save(Deposit deposit);



}
