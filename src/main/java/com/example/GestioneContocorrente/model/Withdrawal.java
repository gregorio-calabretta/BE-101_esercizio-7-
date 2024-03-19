package com.example.GestioneContocorrente.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "withdrawals")
public class Withdrawal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "withdrawal_id")
    private UUID withdrawal_id;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "date")
    private Timestamp date;
}
