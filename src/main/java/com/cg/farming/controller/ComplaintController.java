package com.cg.farming.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.farming.entity.Complaint;
import com.cg.farming.service.ComplaintServiceImpl;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

	@Autowired
	ComplaintServiceImpl compServ;
	
	@PostMapping("/add")
	public ResponseEntity<Complaint> addComplaint(@RequestBody Complaint complaint) {
		Complaint ck =compServ.addComplaint(complaint);
		return new ResponseEntity<>(ck, HttpStatus.CREATED); 
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Complaint>> getAllComplaint() {
		List<Complaint> comp = compServ.getAllComplaint();
		return new ResponseEntity<>(comp, HttpStatus.OK);
	}
}
