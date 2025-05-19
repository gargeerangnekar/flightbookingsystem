package com.capgemini.flightbookingsystem.testcontrollers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.capgemini.flightbookingsystem.controllers.UserController;
import com.capgemini.flightbookingsystem.entities.User;
import com.capgemini.flightbookingsystem.services.UserService;

class UserControllerTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	private User user1;
	private User user2;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		user1 = new User(1, "Rahul", "rahul@gmail.com", "pass123", "1234567890", "P123456");
		user2 = new User(2, "Rani", "rani@gmail.com", "pass456", "9876543210", "P654321");
	}

	@Test
	@DisplayName("Should return list of all users")
	void testGetAllUsers() {
		List<User> users = Arrays.asList(user1, user2);
		when(userService.getAllUsers()).thenReturn(users);

		ResponseEntity<List<User>> response = userController.getAllUsers();

		assertEquals(200, response.getStatusCode().value());
		assertEquals(2, response.getBody().size());
		assertEquals("Rani", response.getBody().get(1).getName());
	}

	@Test
	@DisplayName("Should add a new user and return it")
	void testAddUser() {
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);

		when(userService.createUser(user1)).thenReturn(user1);

		ResponseEntity<User> response = userController.createUser(user1, bindingResult);

		assertEquals(201, response.getStatusCode().value());
		assertNotNull(response.getBody());

		User savedUser = (User) response.getBody();
		assertEquals("Rahul", savedUser.getName());
	}

	@Test
	@DisplayName("Should get user by ID")
	void testGetUserById() {
		when(userService.getUserById(1)).thenReturn(user1);

		ResponseEntity<User> response = userController.getUserById(1);

		assertEquals(200, response.getStatusCode().value());
		assertEquals("Rahul", response.getBody().getName());
	}

	@Test
	@DisplayName("Should update user by ID")
	void testUpdateUser() {
		User updatedUser = new User(1, "Rahul Updated", "rahul.updated@gmail.com", "newpass", "9999999999", "P999999");

		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);

		when(userService.putUser(1, updatedUser)).thenReturn(updatedUser);

		ResponseEntity<User> response = userController.updateUser(1, updatedUser, bindingResult);

		assertEquals(200, response.getStatusCode().value());

		User userBody = (User) response.getBody();
		assertNotNull(userBody);
		assertEquals("Rahul Updated", userBody.getName());
	}

	@Test
	@DisplayName("Should delete user by ID")
	void testDeleteUser() {
		doNothing().when(userService).deleteUser(1);

		ResponseEntity<User> response = userController.deleteUser(1);

		assertEquals(204, response.getStatusCode().value());
	}
	@Test
	@DisplayName("Should patch user by ID")
	void testPatchUser() {
	    User patchUser = new User();
	    patchUser.setName("Patched Name");

	    BindingResult bindingResult = mock(BindingResult.class);
	    when(bindingResult.hasErrors()).thenReturn(false);

	    when(userService.patchUser(1, patchUser)).thenReturn(patchUser);

	    ResponseEntity<User> response = userController.patchUser(1, patchUser, bindingResult);

	    assertEquals(200, response.getStatusCode().value());
	    assertEquals("Patched Name", response.getBody().getName());
	}

}
