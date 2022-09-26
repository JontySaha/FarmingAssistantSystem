package com.cg.farming.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.farming.entity.User;


public interface IUserRepo extends JpaRepository<User,Integer>{
	Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}
