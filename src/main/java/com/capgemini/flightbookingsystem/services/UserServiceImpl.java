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

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Integer id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Get : User not found with ID : " + id));
	}

	@Override
	public User createUser(@Valid User user) {
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new EmailAlreadyExist("User with Email : "+user.getEmail()+"Already Exist.");
		}
		return userRepository.save(user);
	}

	@Override
	public User putUser(Integer id, @Valid User user) {
		User existing = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Update : User not found with Id : " + id));
		existing.setName(user.getName());
		existing.setEmail(user.getEmail());
		existing.setPassword(user.getPassword());
		existing.setPhoneNumber(user.getPhoneNumber());
		existing.setPassportNumber(user.getPassportNumber());

		return userRepository.save(existing);
	}

	@Override
	public User patchUser(Integer id, @Valid User patch) {
		User existing = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Patch : User not found with Id : " + id));

		if (patch.getName() != null) {
			existing.setName(patch.getName());
		}
		if (patch.getEmail() != null) {
			existing.setEmail(patch.getEmail());
		}
		if (patch.getPassword() != null) {
			existing.setPassword(patch.getPassword());
		}
		if (patch.getPhoneNumber() != null) {
			existing.setPhoneNumber(patch.getPhoneNumber());
		}
		if (patch.getPassportNumber() != null) {
			existing.setPassportNumber(patch.getPassportNumber());
		}
		return userRepository.save(existing);
	}
	
	@Override
	public void deleteUser(Integer id) {
	    Optional<User> user = userRepository.findById(id);
	    if (user.isPresent()) {
	        userRepository.deleteById(id);
	    } else {
	        throw new UserNotFoundException("Cannot delete. User not found with ID: " + id);
	    }
	}

	
	
}
