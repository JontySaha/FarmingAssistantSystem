package com.cg.farming.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.farming.entity.Advertisement;

@Repository
public interface IAdvertisementRepo extends JpaRepository<Advertisement,Integer> {

}