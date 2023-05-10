package com.space_gaze.backend.security;

import com.space_gaze.backend.entity.User;
import com.space_gaze.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository
				.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + email + " no existe."));
		
		return new UserDetailsImpl(user);
	}

	
}
