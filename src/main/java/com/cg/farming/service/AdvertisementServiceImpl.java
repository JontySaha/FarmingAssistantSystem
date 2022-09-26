package com.cg.farming.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.farming.entity.Advertisement;
import com.cg.farming.exception.AdvertisementNotFoundException;
import com.cg.farming.repo.IAdvertisementRepo;

@Service
public class AdvertisementServiceImpl implements IAdvertisementService{
		
	@Autowired
	IAdvertisementRepo advtRepo;
	
	@Override
	public Advertisement addAdvertisement(Advertisement advt) {
		return advtRepo.save(advt);
	}


	@Override
	public Advertisement updateAdvertisement(int id, Advertisement advt) throws AdvertisementNotFoundException {
		Optional<Advertisement> advertisement = advtRepo.findById(id);

		if (advertisement.isPresent()) {
			Advertisement adv = advertisement.get();
			adv.setCropType(advt.getCropType());
			adv.setQuantity(advt.getQuantity());
			adv.setWeight(advt.getWeight());
			adv.setPrice(advt.getPrice());
		    return advtRepo.save(adv);
		} else {
			throw new AdvertisementNotFoundException("Advertisement not found with given id: " + id);
		}
	}

	@Override
	public Advertisement deleteAdvertisement(int id) throws AdvertisementNotFoundException {
		Optional<Advertisement> advtOpt = advtRepo.findById(id);
		if (advtOpt.isPresent()) {
		Advertisement adv = advtOpt.get();
			advtRepo.deleteById(id);
			return adv;
		} else {
			throw new AdvertisementNotFoundException("Advertisement not found with given id: " + id);
		}
	

	}

	@Override
	public List<Advertisement> getAllAdvertisement() {
		List<Advertisement> advertisements = advtRepo.findAll();
		return advertisements;
	}

}
