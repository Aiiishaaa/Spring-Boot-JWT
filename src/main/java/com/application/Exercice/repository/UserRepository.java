package com.application.Exercice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.Exercice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}