package com.example.GestioneContocorrente.repository;

import com.example.GestioneContocorrente.dtos.TransactionDto;
import com.example.GestioneContocorrente.exception.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TransactionRepoImpl implements TransactionRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    public TransactionRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<TransactionDto> findLast5TransactionsByUserIdAndBankAccountId(Long userId, Long bankAccountId) throws ResourceNotFoundException {
        String query = "SELECT * FROM (SELECT user_id, bank_account_id,amount, date FROM deposit " +
                     "WHERE :user_id = deposit.user_id  AND :bank_account_id = deposit.bank_account_id " +
                     "UNION ALL " +
                     "SELECT user_id, bank_account_id,amount, date FROM withdrawal " +
                     "WHERE :user_id = withdrawal.user_id AND :bank_account_id = withdrawal.bank_account_id) " +
                     "AS transazioni_combinate " +
                     "ORDER BY date DESC LIMIT 5";

      /*  String query = "SELECT b.user_id AS userid, CASE WHEN SIGN(d.amount)>0 THEN 'deposit' " +
                "WHEN SIGN(w.amount)<0 THEN 'withdrawal' ELSE NULL END AS type,CASE WHEN d.id IS NOT NULL THEN d.amount WHEN w.id IS NOT NULL THEN w.amount ELSE NULL END " +
                "AS amount, CASE WHEN d.id IS NOT NULL THEN d.id WHEN w.id IS NOT NULL THEN w.id ELSE NULL END AS id, CASE WHEN d.date > w.date THEN d.date ELSE w.date " +
                "END AS transaction_date FROM bank_account b LEFT JOIN deposit d ON b.id = d.bank_account_id AND b.user_id = d.user_id " +
                "LEFT JOIN withdrawal w ON b.id = w.bank_account_id AND b.user_id = w.user_id WHERE b.id = :bank_account_id AND b.user_id = :user_id " +
                "ORDER BY transaction_date DESC LIMIT 50";*/

        List<TransactionDto> resultList = entityManager.createNativeQuery(query, TransactionDto.class)
                .setParameter("user_id", userId)
                .setParameter("bank_account_id", bankAccountId)
                .getResultList();

        if (resultList.isEmpty()) {
            throw new ResourceNotFoundException("Transactions not found");
        }

        return resultList.stream()
                .map(result -> {
                    long resultUserId =  result.getUser_id();
                    long resultBankAccountId = result.getBank_account_id();
                    long resultAmount = result.getAmount();
                    Date resultDate=  result.getDate();
                    String resultType = result.getType();

                    return new TransactionDto(resultBankAccountId,resultUserId ,resultAmount,resultDate,resultType );
                })
                .collect(Collectors.toList());
    }
}