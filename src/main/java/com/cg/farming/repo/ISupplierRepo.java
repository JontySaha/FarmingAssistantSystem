package com.cg.farming.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.farming.entity.Supplier;

public interface ISupplierRepo extends JpaRepository<Supplier,Integer>{
	Optional<Supplier> findByUsername(String username);
    Boolean existsByUsername(String username);
}
