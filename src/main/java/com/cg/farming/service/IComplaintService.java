package com.cg.farming.service;

import java.util.List;

import com.cg.farming.entity.Complaint;

public interface IComplaintService {
	Complaint addComplaint(Complaint comp);
	List<Complaint> getAllComplaint();
}
