package com.example.GestioneContocorrente.repository;

import com.example.GestioneContocorrente.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface BankAccountRepo extends JpaRepository<BankAccount, UUID> {
    List<BankAccount> findAll();
    BankAccount save(BankAccount bankAccount);

    Optional<BankAccount> findById(UUID id);

    void deleteById(UUID id);
}
