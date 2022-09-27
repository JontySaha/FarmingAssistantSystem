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

import com.cg.farming.entity.Role;
import com.cg.farming.entity.Supplier;
import com.cg.farming.exception.SupplierNotFoundException;
import com.cg.farming.repo.ISupplierRepo;

@Service
public class SupplierServiceImpl implements UserDetailsService,ISupplierService {

	@Autowired
	ISupplierRepo supplierRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Supplier supplier = supplierRepo.findByUsername(username)
				.orElseThrow(()->new UsernameNotFoundException("User not found with username:" + username));
				return new org.springframework.security.core.userdetails.User(supplier.getUsername(),
						supplier.getPassword(), mapRolesToAuthorities(supplier.getRoles()));
	}
	
	 private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
	        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public Supplier deleteSupplier(int id) throws SupplierNotFoundException {
		Optional<Supplier> supOpt = supplierRepo.findById(id);
		if (supOpt.isPresent()) {
			Supplier sup = supOpt.get();
			supplierRepo.deleteById(id);
			return sup;
		} else {
			throw new SupplierNotFoundException("Suplier not found with given id: " + id);
		}
	}

	@Override
	public Supplier updateSupplier(int id, Supplier supl) throws SupplierNotFoundException {
		Optional<Supplier > supplier  = supplierRepo.findById(id);

		if (supplier.isPresent()) {
			Supplier sup = supplier.get();
			sup.setUsername(supl.getUsername());
			sup.setPassword(supl.getPassword());
			sup.setAddress(supl.getAddress());
			sup.setPhoneNo(supl.getPhoneNo());
		    return supplierRepo.save(sup);
		} else {
			throw new SupplierNotFoundException("Advertisement not found with given id: " + id);
		}
	}
}
