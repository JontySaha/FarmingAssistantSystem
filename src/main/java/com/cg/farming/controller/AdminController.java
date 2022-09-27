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

import com.cg.farming.dto.AdminSignUpDto;
import com.cg.farming.dto.LoginDto;
import com.cg.farming.entity.Admin;
import com.cg.farming.entity.Role;
import com.cg.farming.repo.IAdminRepo;
import com.cg.farming.repo.IRoleRepo;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
    private AuthenticationManager authenticationManager;

	private static Logger logger = LogManager.getLogger();
	
    @Autowired
    private IAdminRepo adminRepository;

    @Autowired
    private IRoleRepo roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody AdminSignUpDto adminSignUpDto){

        // add check for username exists in a DB
        if(adminRepository.existsByUsername(adminSignUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }       

        // create user object
        Admin admin = new Admin();
        admin.setUsername(adminSignUpDto.getUsername());
        admin.setPassword(passwordEncoder.encode(adminSignUpDto.getPassword()));

       
        logger.info(admin);

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        admin.setRoles(Collections.singleton(roles));
       
        adminRepository.save(admin);

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
