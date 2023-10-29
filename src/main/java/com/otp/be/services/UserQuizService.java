package com.otp.be.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.otp.be.modal.User;
import com.otp.be.modal.UserQuiz;
import com.otp.be.repository.UserQuizRepository;
import com.otp.be.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserQuizService {

private UserQuizRepository userQuizRepository;
	
	public UserQuizService(UserQuizRepository userQuizRepository) {
		this.userQuizRepository = userQuizRepository;
	}
	
	public List<UserQuiz> getUserQuizDetails() {
		return userQuizRepository.findAll();
	}
	
	public UserQuiz getUserQuizById(int userQuizId) {
		return userQuizRepository.findById(userQuizId).get();
	}
	
	public UserQuiz saveUserQuiz(UserQuiz userQuiz) {
		return userQuizRepository.save(userQuiz);
	}
}
