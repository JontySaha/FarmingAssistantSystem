package com.cg.farming.controller;

import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.farming.dto.LoginDto;
import com.cg.farming.dto.SignUpDto;
import com.cg.farming.entity.Farmer;
import com.cg.farming.entity.Role;
import com.cg.farming.repo.IFarmerRepo;
import com.cg.farming.repo.IRoleRepo;

@RestController
@RequestMapping("/farmer")
public class FarmerController {

    @Autowired
    private AuthenticationManager authenticationManager;

	private static Logger logger = LogManager.getLogger();
	
    @Autowired
    private IFarmerRepo farmerRepository;

    @Autowired
    private IRoleRepo roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){

        // add check for username exists in a DB
        if(farmerRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }       

        // create user object
        Farmer farmer = new Farmer();
        farmer.setUsername(signUpDto.getUsername());
        farmer.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        farmer.setAddress(signUpDto.getAddress());
        farmer.setPhoneNo(signUpDto.getPhoneNo());
       
        logger.info(farmer);

        Role roles = roleRepository.findByName("ROLE_FARMER").get();
        farmer.setRoles(Collections.singleton(roles));
       
        farmerRepository.save(farmer);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
    
    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));
        logger.info(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

}
