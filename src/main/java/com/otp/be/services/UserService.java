package com.otp.be.services;

import java.util.*;

import org.springframework.stereotype.Service;

import com.otp.be.modal.User;
import com.otp.be.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> getUserDetails() {
		return userRepository.findAll();
	}
	
	public User getUserById(int userId) {
		return userRepository.findById(userId).get();
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
}
