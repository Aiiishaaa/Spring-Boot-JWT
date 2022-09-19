package com.application.Exercice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.Exercice.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
