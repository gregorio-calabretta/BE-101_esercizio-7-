package com.example.GestioneContocorrente.repository;

import com.example.GestioneContocorrente.dtos.TransactionDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Repository
public class TransactionRepoImpl implements TransactionRepo{
    @PersistenceContext
    private final EntityManager entityManager;

    public TransactionRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<TransactionDto> findLast5TransactionsByUserIdAndBankAccountId(Long userId, Long bankAccountId) {
        String sql ="SELECT * FROM (SELECT user_id, bank_account_id,amount, date FROM deposit WHERE :user_id = deposit.user_id  AND :bank_account_id = deposit.bank_account_id UNION ALL SELECT user_id, bank_account_id,amount, date FROM withdrawal WHERE :user_id = withdrawal.user_id AND :bank_account_id = withdrawal.bank_account_id) AS transazioni_combinate ORDER BY date DESC LIMIT 5";

        List<Object[]> resultList = entityManager.createNativeQuery(sql)
                .setParameter("user_id", userId)
                .setParameter("bank_account_id", bankAccountId)
                .getResultList();

        List<TransactionDto> transactions = new LinkedList<>();

        for (Object[] result : resultList) {
            Long resultUserId = (Long) result[0];
            Long resultBankAccountId = (Long) result[1];
            Long resultAmount =(Long) result[2];
            Date resultDate = (Date) result[3];
            TransactionDto transactionDto = new TransactionDto(resultUserId, resultBankAccountId, resultAmount,resultDate);
            transactions.add(transactionDto);
        }
        return transactions;
    }
}
