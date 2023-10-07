package com.otp.be.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.otp.be.modal.User;
import com.otp.be.services.UserService;

@RestController
public class ApplicationController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getUserDetails() {
		return userService.getUserDetails();
	}
	
	@GetMapping("/users/{userId}")
	public User getUserById(@PathVariable int userId) {
		return userService.getUserById(userId);
	}
	
	@PostMapping("/saveUser")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
}
