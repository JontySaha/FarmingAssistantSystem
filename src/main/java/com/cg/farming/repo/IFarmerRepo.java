package com.cg.farming.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.farming.entity.Farmer;


public interface IFarmerRepo extends JpaRepository<Farmer,Integer>{
	Optional<Farmer> findByUsername(String username);
    Boolean existsByUsername(String username);
}
