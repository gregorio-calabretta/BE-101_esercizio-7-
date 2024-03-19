package com.example.GestioneContocorrente.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bank_accounts")

public class BankAccount {
    @Id
    @Column(name = "bank_account_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID bank_account_id;

    @Column(name = "balance")
    private long balance;

    @Column(name = "created_at")
    private Timestamp created_at;


}
