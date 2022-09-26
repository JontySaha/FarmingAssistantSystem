package com.cg.farming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.farming.entity.Complaint;
import com.cg.farming.repo.IComplaintRepo;

@Service
public class ComplaintServiceImpl implements IComplaintService {

	@Autowired
	IComplaintRepo compRepo;
	
	@Override
	public Complaint addComplaint(Complaint comp) {
		return compRepo.save(comp);
	}

	@Override
	public List<Complaint> getAllComplaint() {
		return compRepo.findAll();
	}

}
