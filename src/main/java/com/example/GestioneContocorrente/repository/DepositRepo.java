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

    @Query(value = "SELECT * FROM (SELECT user_id, bank_account_id,amount, date FROM deposit WHERE :user_id = deposit.user_id  AND :bank_account_id = deposit.bank_account_id UNION ALL SELECT user_id, bank_account_id,amount, date FROM withdrawal WHERE :user_id = withdrawal.user_id AND :bank_account_id = withdrawal.bank_account_id) AS transazioni_combinate ORDER BY date DESC LIMIT 5", nativeQuery = true)
    List<TransactionDto> getLast5TransactionsByUserIdAndBankAccountId(@Param("user_id") Long userId, @Param("bank_account_id") Long bankAccountId);


}
