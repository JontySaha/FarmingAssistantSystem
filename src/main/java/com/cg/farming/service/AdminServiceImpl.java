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

import com.cg.farming.entity.Admin;
import com.cg.farming.entity.Role;
import com.cg.farming.exception.AdminNotFoundException;
import com.cg.farming.repo.IAdminRepo;

@Service
public class AdminServiceImpl implements UserDetailsService,IAdminService {

	@Autowired
	IAdminRepo admRepo;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = admRepo.findByUsername(username)
				.orElseThrow(()->new UsernameNotFoundException("User not found with username:" + username));
				return new org.springframework.security.core.userdetails.User(admin.getUsername(),
						admin.getPassword(), mapRolesToAuthorities(admin.getRoles()));
	}
	
	 private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
	        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
	
	@Override
	public Admin deleteAdmin(int id) throws AdminNotFoundException {
		
		Optional<Admin> advtOpt = admRepo.findById(id);
		if (advtOpt.isPresent()) {
			Admin adv = advtOpt.get();
			admRepo.deleteById(id);
			return adv;
		} else {
			throw new AdminNotFoundException("Advertisement not found with given id: " + id);
		}
	
     }
	
	@Override
	public Admin updateAdmin(int id, Admin ad) throws AdminNotFoundException {
		Optional<Admin> admin = admRepo.findById(id);

		if (admin.isPresent()) {
			Admin adm = admin.get();
			adm.setUsername(ad.getUsername());
			adm.setPassword(ad.getPassword());
		    return admRepo.save(adm);
		} else {
			throw new AdminNotFoundException("Advertisement not found with given id: " + id);
		}
	}

}
