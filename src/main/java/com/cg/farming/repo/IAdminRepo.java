package com.cg.farming.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.farming.entity.Admin;

@Repository
public interface IAdminRepo extends JpaRepository<Admin,Integer>{
	Optional<Admin> findByUsername(String username);
	boolean existsByUsername(String username);
}
