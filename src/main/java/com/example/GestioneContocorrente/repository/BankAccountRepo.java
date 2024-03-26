package com.example.GestioneContocorrente.repository;

import com.example.GestioneContocorrente.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepo extends JpaRepository<BankAccount, Long> {
    List<BankAccount> findAll();
    BankAccount save(BankAccount bankAccount);

    Optional<BankAccount> findById(Long id);

    void deleteById(Long id);
}
