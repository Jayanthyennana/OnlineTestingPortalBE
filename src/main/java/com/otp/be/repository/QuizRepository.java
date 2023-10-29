package com.otp.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.otp.be.modal.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer>{
	

}
