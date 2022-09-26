package com.cg.farming.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.farming.entity.Role;

public interface IRoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}