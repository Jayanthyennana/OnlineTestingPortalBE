package com.otp.be.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.otp.be.modal.Score;
import com.otp.be.repository.ScoreRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ScoreService {
	private ScoreRepository scoreRepository;
	
	public ScoreService(ScoreRepository scoreRepository) {
		this.scoreRepository = scoreRepository;
	}
	
	public List<Score> getScoreDetails() {
		return scoreRepository.findAll();
	}
	
	public Score getScoreById(int scoreId) {
		return scoreRepository.findById(scoreId).get();
	}
	
	public Score saveScore(Score score) {
		return scoreRepository.save(score);
	}

}
