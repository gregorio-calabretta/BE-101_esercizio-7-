package com.example.GestioneContocorrente.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bank_account")

public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "balance")
    private long balance;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE,mappedBy = "bankAccount")
    private List<Withdrawal> withdrawals;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE,mappedBy = "bankAccount")
    private List<Deposit> deposits;


}
