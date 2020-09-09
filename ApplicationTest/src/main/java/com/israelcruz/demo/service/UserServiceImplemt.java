package com.israelcruz.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.israelcruz.demo.entity.User;
import com.israelcruz.demo.repository.UserRepository;

@Service
public class UserServiceImplemt implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public Iterable<User> getAllUsers() {
		//return userRepository.findAllByStatus("active");
		return userRepository.findAll();
	}

}
