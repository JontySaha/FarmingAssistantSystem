package com.cg.farming.service;

import java.util.List;

import com.cg.farming.entity.Advertisement;
import com.cg.farming.exception.AdvertisementNotFoundException;

public interface IAdvertisementService  {

	Advertisement addAdvertisement(Advertisement advt);
	Advertisement updateAdvertisement(int id,Advertisement advt) throws AdvertisementNotFoundException;
	Advertisement deleteAdvertisement(int id) throws AdvertisementNotFoundException; 
	List<Advertisement> getAllAdvertisement();
}