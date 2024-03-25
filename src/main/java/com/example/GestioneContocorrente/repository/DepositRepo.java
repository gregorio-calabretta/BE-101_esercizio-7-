package com.example.GestioneContocorrente.repository;

import com.example.GestioneContocorrente.dtos.TransactionDto;
import com.example.GestioneContocorrente.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface DepositRepo extends JpaRepository<Deposit, UUID> {
    List<Deposit> findAll();
    Deposit save(Deposit deposit);

    @Query(value = "SELECT * FROM (SELECT user_id, bank_account_id, date, amount, 'deposit' AS transactionType FROM deposit WHERE user_id LIKE CONCAT(:userId,'%') AND bank_account_id LIKE CONCAT(:bankAccountId,'%') UNION ALL SELECT user_id, bank_account_id, date, amount, 'withdrawal' AS transactionType FROM withdrawal WHERE user_id = CONCAT(:userId,'%')  AND bank_account_id = CONCAT(:bankAccountId,'%')) AS combined_transaction ORDER BY date DESC LIMIT 5",nativeQuery = true)
    List<TransactionDto> getLast5TransactionsByUserIdAndBankAccountId(@Param("userId")UUID userId,@Param("bankAccountId") UUID bankAccountId);
}
