package com.edgar.jwtdemo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edgar.jwtdemo.model.User;
import com.edgar.jwtdemo.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private UserRepository userRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User 404");
		}
		return new UserDetailsImpl(user);
		
//		Map<String, String> users = new HashMap<>();
//		users.put("user", "user");
//		users.put("foo", "foo");
//		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}

}
