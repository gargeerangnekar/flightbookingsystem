package com.capgemini.flightbookingsystem.services;

import java.util.List;

import com.capgemini.flightbookingsystem.entities.User;

public interface UserService {
	List<User> getAllUsers();

	User getUserById(Long id);

	User createUser(User user);

	User putUser(Long id, User user);
	
	User patchUser(Long id, User user);

	void deleteUser(Long id);
}