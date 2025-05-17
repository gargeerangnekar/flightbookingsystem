package com.capgemini.flightbookingsystem.securities;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capgemini.flightbookingsystem.entities.AirLineAdmin;
import com.capgemini.flightbookingsystem.entities.User;
import com.capgemini.flightbookingsystem.repositories.AirLineAdminRepository;
import com.capgemini.flightbookingsystem.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;
	private AirLineAdminRepository airLineAdminRepository;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository, AirLineAdminRepository airLineAdminRepository) {
		super();
		this.userRepository = userRepository;
		this.airLineAdminRepository = airLineAdminRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Set<GrantedAuthority> authorities = new HashSet<>();

		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
					authorities);

		}

		Optional<AirLineAdmin> optionalAdmin = airLineAdminRepository.findByAirlineEmail(email);
		if (optionalAdmin.isPresent()) {
			AirLineAdmin admin = optionalAdmin.get();
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			return new org.springframework.security.core.userdetails.User(admin.getAirlineEmail(), admin.getPassword(),
					authorities);
		}
		throw new UsernameNotFoundException("User not found with email :" + email);

	}

}
