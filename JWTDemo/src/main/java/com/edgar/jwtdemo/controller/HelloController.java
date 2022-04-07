package com.edgar.jwtdemo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edgar.jwtdemo.model.AuthenticationRequest;
import com.edgar.jwtdemo.model.AuthenticationResponse;
import com.edgar.jwtdemo.util.JwtTokenUtil;

@RestController
public class HelloController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello world";
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> loginRequest(@Valid @RequestBody AuthenticationRequest request, 
			Errors errors) throws Exception {
		
		if (errors.hasErrors()) {
			throw new Exception("Username or password is empty");
		}
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (AuthenticationException ex) {
			throw new Exception("Incorrect username or password");
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
