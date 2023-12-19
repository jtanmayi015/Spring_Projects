package com.cdac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.entity.*;
import com.cdac.repository.RegistrationRepository;

@CrossOrigin
@RestController
public class Registrationcontroller {
	@Autowired
	public RegistrationRepository rr;
	@PostMapping("/Registration")
	public String add(@RequestBody Registration user) {
		rr.save(user);
		return null;
	}
	@PostMapping("/login")
	public ResponseEntity<Registration> login(@RequestBody Registration user){
		try {
			Registration ruser= rr.findByFirstName(user.getFirstName());
			if(ruser.getPassword().equals(user.getPassword())){
				return new ResponseEntity<Registration>(HttpStatus.OK);
			}
			else return new ResponseEntity<Registration>(HttpStatus.ACCEPTED);	
		}catch(Exception e) {
			return new ResponseEntity<Registration>(HttpStatus.ACCEPTED);			
		}
	}
}
