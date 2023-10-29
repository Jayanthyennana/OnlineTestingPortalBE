package com.otp.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otp.be.modal.Questions;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Integer> {
	

}
