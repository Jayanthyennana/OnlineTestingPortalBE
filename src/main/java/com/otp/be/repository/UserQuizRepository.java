package com.otp.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otp.be.modal.UserQuiz;

@Repository
public interface UserQuizRepository extends JpaRepository<UserQuiz, Integer> {
	

}
