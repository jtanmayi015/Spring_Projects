package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.User;
import com.app.repository.*;
@Service
public class RegistrationImp implements RegistrationInterface {
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User saveUser(User user) {
		return userRepo.save(user);
	}

}
