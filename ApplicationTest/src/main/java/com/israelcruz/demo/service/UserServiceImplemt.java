package com.israelcruz.demo.service;

import java.util.Optional;

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
	
	@Override
	public User createUser(User user) throws Exception {
		if(checkUsernameAvailable(user) && checkIfPasswordMatch(user)) {
			user = userRepository.save(user);
		}
		return user;
	}
	
	private boolean checkUsernameAvailable(User user) throws Exception {
		Optional<User> userFound = userRepository.findByUsername(user.getUsername());
		
		if(userFound.isPresent()) {
			throw new Exception("Username not available.");
		}
		return true;
	}
	
	private boolean checkIfPasswordMatch(User user) throws Exception {
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			throw new Exception("Password doesn't match.");
		}
		return true;
	}

}
