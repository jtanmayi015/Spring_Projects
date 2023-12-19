package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.entity.User;
import com.app.service.RegistrationImp;
@RequestMapping("/register")
public class UserController {
	@Autowired
	private RegistrationImp registrationImpl;
   

    @PostMapping("/add")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // You can add validation and password hashing here
    	 registrationImpl.saveUser(user);
        return ResponseEntity.ok("User registered successfully.");
    }

    // Other methods as needed
}
