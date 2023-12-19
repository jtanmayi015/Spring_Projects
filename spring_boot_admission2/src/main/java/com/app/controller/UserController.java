package com.app.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.app.custom_exceptions.*;
import com.app.custom_exceptions.UserNotFoundException;
import com.app.entities.User;
import com.app.repository.UserRepo;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
  @Autowired
  private UserRepo userRepo;
  
  @PostMapping("/user")
  User newUser(@RequestBody User newUser) {
	  return userRepo.save(newUser);
  }
  
  @GetMapping("/users")
  List<User> getAllUsers(){
	return userRepo.findAll();
	  
  }
  @GetMapping("/user/{id}")
  User getUserById(@PathVariable Long id) {
	  return userRepo.findById(id).orElseThrow(()->new UserNotFoundException(id));
  }
  
  @PutMapping("/usre/{id}")
  User updateUser(@RequestBody User newUser, @PathVariable Long id) {
	  return userRepo.findById(id).map(user -> {
		  user.setUsername(newUser.getUsername());
		  user.setName(newUser.getName());
		  user.setEmail(newUser.getEmail());
		  return userRepo.save(user);
		  
	  }).orElseThrow(()->new UserNotFoundException(id));
			  
  }
} 
