package com.israelcruz.demo.service;

import com.israelcruz.demo.entity.User;

public interface UserService {
	public Iterable<User> getAllUsers();
	public User createUser(User user) throws Exception;
}
