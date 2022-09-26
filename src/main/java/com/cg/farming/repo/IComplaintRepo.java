package com.cg.farming.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.farming.entity.Complaint;

@Repository
public interface IComplaintRepo extends JpaRepository<Complaint,Integer>{

}
