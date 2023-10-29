package com.otp.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otp.be.modal.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {

}
