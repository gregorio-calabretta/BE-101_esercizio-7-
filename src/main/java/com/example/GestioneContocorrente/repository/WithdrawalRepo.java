package com.example.GestioneContocorrente.repository;

import com.example.GestioneContocorrente.model.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WithdrawalRepo extends JpaRepository<Withdrawal, Long> {
    List<Withdrawal> findAll();
    Withdrawal save(Withdrawal withdrawal);

}
