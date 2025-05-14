package com.capgemini.flightbookingsystem.services;

import java.util.List;

import com.capgemini.flightbookingsystem.entities.User;

public interface UserService {
	List<User> getAllUsers();

	User getUserById(Integer id);

	User createUser(User user);

	User putUser(Integer id, User user);
	
	User patchUser(Integer id, User user);

	void deleteUser(Integer id);
}