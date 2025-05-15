package com.capgemini.flightbookingsystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.flightbookingsystem.entities.User;
import com.capgemini.flightbookingsystem.exceptions.EmailAlreadyExist;
import com.capgemini.flightbookingsystem.exceptions.UserNotFoundException;
import com.capgemini.flightbookingsystem.repositories.UserRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getAllUsers() {
		log.info("Fetching all users from the database");
		List<User> users = userRepository.findAll();
		log.debug("Total users retrieved: {}", users.size());
		return users;
	}

	@Override
	public User getUserById(Integer id) {
		log.info("Fetching user with ID: {}", id);
		return userRepository.findById(id)
				.orElseThrow(() -> {
					log.warn("User not found with ID: {}", id);
					return new UserNotFoundException("Get : User not found with ID : " + id);
				});
	}

	@Override
	public User createUser(@Valid User user) {
		log.info("Creating user with email: {}", user.getEmail());
		if (userRepository.existsByEmail(user.getEmail())) {
			log.warn("Email already exists: {}", user.getEmail());
			throw new EmailAlreadyExist("User with Email : " + user.getEmail() + " Already Exist.");
		}
		User saved = userRepository.save(user);
		log.debug("User created successfully with ID: {}", saved.getUserId());
		return saved;
	}

	@Override
	public User putUser(Integer id, @Valid User user) {
		log.info("Updating user (full update) with ID: {}", id);
		User existing = userRepository.findById(id)
				.orElseThrow(() -> {
					log.warn("User not found for full update with ID: {}", id);
					return new UserNotFoundException("Update : User not found with Id : " + id);
				});

		existing.setName(user.getName());
		existing.setEmail(user.getEmail());
		existing.setPassword(user.getPassword());
		existing.setPhoneNumber(user.getPhoneNumber());
		existing.setPassportNumber(user.getPassportNumber());

		User updated = userRepository.save(existing);
		log.debug("User with ID {} updated successfully", id);
		return updated;
	}

	@Override
	public User patchUser(Integer id, @Valid User patch) {
		log.info("Patching user with ID: {}", id);
		User existing = userRepository.findById(id)
				.orElseThrow(() -> {
					log.warn("User not found for patch with ID: {}", id);
					return new UserNotFoundException("Patch : User not found with Id : " + id);
				});

		if (patch.getName() != null) {
			existing.setName(patch.getName());
			log.debug("Updated name to: {}", patch.getName());
		}
		if (patch.getEmail() != null) {
			existing.setEmail(patch.getEmail());
			log.debug("Updated email to: {}", patch.getEmail());
		}
		if (patch.getPassword() != null) {
			existing.setPassword(patch.getPassword());
			log.debug("Updated password");
		}
		if (patch.getPhoneNumber() != null) {
			existing.setPhoneNumber(patch.getPhoneNumber());
			log.debug("Updated phone number to: {}", patch.getPhoneNumber());
		}
		if (patch.getPassportNumber() != null) {
			existing.setPassportNumber(patch.getPassportNumber());
			log.debug("Updated passport number to: {}", patch.getPassportNumber());
		}

		User updated = userRepository.save(existing);
		log.debug("User with ID {} patched successfully", id);
		return updated;
	}
	
	@Override
	public void deleteUser(Integer id) {
		log.info("Attempting to delete user with ID: {}", id);
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			userRepository.deleteById(id);
			log.debug("User with ID {} deleted successfully", id);
		} else {
			log.warn("Delete failed. User not found with ID: {}", id);
			throw new UserNotFoundException("Cannot delete. User not found with ID: " + id);
		}
	}
}
