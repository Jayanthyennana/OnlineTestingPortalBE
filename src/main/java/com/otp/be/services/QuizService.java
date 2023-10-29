package com.otp.be.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.otp.be.modal.Quiz;
import com.otp.be.repository.QuizRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class QuizService {
	
	private QuizRepository quizRepository;
	
	public QuizService(QuizRepository quizRepository) {
		this.quizRepository = quizRepository;
	}
	
	public List<Quiz> getQuizDetails() {
		return quizRepository.findAll();
	}
	
	public Quiz getQuizById(int quizId) {
		return quizRepository.findById(quizId).get();
	}
	
	public Quiz saveQuiz(Quiz quiz) {
		return quizRepository.save(quiz);
	}

}
