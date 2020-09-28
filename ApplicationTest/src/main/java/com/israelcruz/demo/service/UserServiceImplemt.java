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
	public User createUser(User user) throws Exception {
		if(checkUsernameAvailable(user) && checkIfPasswordMatch(user)) {
			user = userRepository.save(user);
		}
		return user;
	}

	@Override
	public User updateUser(User fromUser) throws Exception {
		User toUser = getUserById(fromUser.getId());
		mapUser(fromUser, toUser);
		
		return userRepository.save(toUser);
	}
	
	// Mapear todo menos el password
	protected void mapUser(User from, User to) {
		to.setUsername(from.getUsername());
		to.setFirstName(from.getFirstName());
		to.setLastName(from.getLastName());
		to.setEmail(from.getEmail());
		to.setRoles(from.getRoles());
	}
	
	@Override
	public Iterable<User> getAllUsers() {
		//return userRepository.findAllByStatus("active");
		return userRepository.findAll();
	}
	
	@Override
	public User getUserById(Long id) throws Exception {
		return userRepository.findById(id).orElseThrow(() -> new Exception("User doesn't exist."));
	}
	
	private boolean checkUsernameAvailable(User user) throws Exception {
		Optional<User> userFound = userRepository.findByUsername(user.getUsername());
		
		if(userFound.isPresent()) {
			throw new Exception("Username not available.");
		}
		return true;
	}
	
	private boolean checkIfPasswordMatch(User user) throws Exception {
		if(user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()) {
			throw new Exception("Confirm password es obligatorio");
		}
		
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			throw new Exception("Password doesn't match.");
		}
		return true;
	}

	@Override
	public void deleteUser(Long id) throws Exception {
		User user = getUserById(id);
		
		userRepository.delete(user);
	}
}
