package com.capgemini.flightbookingsystem.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.capgemini.flightbookingsystem.entities.User;
import com.capgemini.flightbookingsystem.repositories.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userService;

	private User user1;
	private User user2;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		user1 = new User(1, "Rahul Dadge", "rahul.dadge@example.com", "pass123", "1234567890", "P123456");
		user2 = new User(2, "Shivam Patil", "shivam.patil@example.com", "pass456", "9876543210", "P654321");
	}

	@Test
	@DisplayName("Should return all users")
	void testGetAllUsers() {
		when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

		List<User> users = userService.getAllUsers();

		assertEquals(2, users.size());
		assertEquals("Shivam Patil", users.get(1).getName());
	}

	@Test
	@DisplayName("Should save and return user")
	void testSaveUser() {
		when(userRepository.save(user1)).thenReturn(user1);

		User savedUser = userService.createUser(user1);

		assertNotNull(savedUser);
		assertEquals("Rahul Dadge", savedUser.getName());
	}

	@Test
	@DisplayName("Should get user by ID")
	void testGetUserById() {
		when(userRepository.findById(1)).thenReturn(Optional.of(user1));

		User foundUser = userService.getUserById(1);

		assertNotNull(foundUser);
		assertEquals("Rahul Dadge", foundUser.getName());
	}

	@Test
	@DisplayName("Should update existing user")
	void testUpdateUser() {
		User updatedUser = new User(1, "Rahul Updated", "rahul.updated@example.com", "newpass", "9999999999",
				"P999999");

		when(userRepository.findById(1)).thenReturn(Optional.of(user1));

		when(userRepository.save(any(User.class))).thenReturn(updatedUser);

		User result = userService.putUser(1, updatedUser);

		assertNotNull(result);
		assertEquals("Rahul Updated", result.getName());
	}

	@Test
	@DisplayName("Should delete existing user without exception")
	void testDeleteUser() {
		when(userRepository.findById(1)).thenReturn(Optional.of(user1));
		doNothing().when(userRepository).deleteById(1);
		assertDoesNotThrow(() -> userService.deleteUser(1));
	}

}
