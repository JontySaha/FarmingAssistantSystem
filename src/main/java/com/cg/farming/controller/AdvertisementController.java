package com.cg.farming.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.farming.entity.Advertisement;
import com.cg.farming.exception.AdvertisementNotFoundException;
import com.cg.farming.service.AdvertisementServiceImpl;

@RestController
@RequestMapping("/advt")
public class AdvertisementController {
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	AdvertisementServiceImpl advService;
	

	@GetMapping("/getAll")
	ResponseEntity<List<Advertisement>> getAllAdvertisement() {
		List<Advertisement> advertisements= advService.getAllAdvertisement();
		return new ResponseEntity<>(advertisements, HttpStatus.OK); // 200 ok
	}
	
	@PostMapping("/add")
	ResponseEntity<Advertisement> addAdvertisement(@RequestBody Advertisement advt) {
		Advertisement newAdvt = advService.addAdvertisement(advt);
		return new ResponseEntity<>(newAdvt, HttpStatus.CREATED); // 201 created 
	}
	
	@PutMapping("/update/{advId}")
	ResponseEntity<Advertisement> updateAdvertisement(@PathVariable("advId") int advId, @RequestBody Advertisement advt) throws AdvertisementNotFoundException {
		Advertisement updatedAdvt = advService.updateAdvertisement(advId, advt);
		logger.info(updatedAdvt);
		return new ResponseEntity<>(updatedAdvt, HttpStatus.OK); // 200 Ok
	}
	
	@DeleteMapping("/delete/{advId}")
	ResponseEntity<Advertisement> deleteAdvertisement(@PathVariable("advId") int advId) throws AdvertisementNotFoundException{
		Advertisement adv = advService.deleteAdvertisement(advId);
		return new ResponseEntity<>(adv, HttpStatus.OK); //200 ok
	}
}
