package com.otp.be.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.otp.be.modal.Questions;
import com.otp.be.modal.Quiz;
import com.otp.be.repository.QuestionsRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class QuestionsService {
	private QuestionsRepository questionsRepository;
	
	public QuestionsService(QuestionsRepository questionsRepository) {
		this.questionsRepository = questionsRepository;
	}
	
	public List<Questions> getQuestionsDetails() {
		return questionsRepository.findAll();
	}
	
	public Questions getQuestionById(int questionId) {
		return questionsRepository.findById(questionId).get();
	}
	
	public Questions saveQuestions(Questions questions) {
		return questionsRepository.save(questions);
	}

}
