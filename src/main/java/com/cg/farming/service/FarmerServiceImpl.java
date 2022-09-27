package com.cg.farming.service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.farming.entity.Farmer;
import com.cg.farming.entity.Role;
import com.cg.farming.exception.FarmerNotFoundException;
import com.cg.farming.repo.IFarmerRepo;

@Service
public class FarmerServiceImpl implements UserDetailsService,IFarmerService  {

	@Autowired
	IFarmerRepo farmerRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Farmer farmer = farmerRepo.findByUsername(username)
				.orElseThrow(()->new UsernameNotFoundException("User not found with username:" + username));
				return new org.springframework.security.core.userdetails.User(farmer.getUsername(),
						farmer.getPassword(), mapRolesToAuthorities(farmer.getRoles()));
	}
	
	 private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
	        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public Farmer deleteFarmer(int id) throws FarmerNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Farmer updateFarmer(int id, Farmer farmer) throws FarmerNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Farmer deleteFarmer(int id) throws FarmerNotFoundException {
//		Optional<Farmer> farmerOpt = farmerRepo.findById(id);
//		if (farmerOpt.isPresent()) {
//			Farmer farmer = farmerOpt.get();
//			farmerRepo.deleteById(id);
//			return farmer;
//		} else {
//			throw new FarmerNotFoundException("Farmer not found with given id: " + id);
//		}
//	}
//
//	@Override
//	public Farmer updateFarmer(int id, Farmer farmer) throws FarmerNotFoundException {
//		Optional<Farmer > farmerOpt  = farmerRepo.findById(id);
//
//		if (farmerOpt.isPresent()) {
//			Farmer farm = farmerOpt.get();
//			farm.setUsername(farmer.getUsername());
//			farm.setPassword(farmer.getPassword());
//			farm.setAddress(farmer.getAddress());
//			farm.setPhoneNo(farmer.getPhoneNo());
//		    return farmerRepo.save(farm);
//		} else {
//			throw new FarmerNotFoundException("Farmer not found with given id: " + id);
//		}
//	}
}
