package com.example.GestioneContocorrente.repository;

import com.example.GestioneContocorrente.model.Deposit;
import com.example.GestioneContocorrente.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepo extends JpaRepository<User, UUID> {
    List<User> findAll();
    User save(User user);

    Optional<User> getUserById(UUID id);
    Optional<User> findById(UUID id);


}
