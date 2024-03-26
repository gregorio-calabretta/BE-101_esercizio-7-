package com.example.GestioneContocorrente.repository;

import com.example.GestioneContocorrente.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findAll();
    User save(User user);

    Optional<User> getUserById(Long id);
    Optional<User> findById(Long id);


}
