package com.otp.be.modal;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="User_Quiz")
public class UserQuiz {
	@Id
	@Column(name="user_quiz_id")
	private int userQuizId;
	
	@Column(name="quiz_id")
	private int quizId;
	
	@Column(name="created_at")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdAt;
	
	@Column(name="created_by", nullable=true)
	private int createdBy;
	
	@Column(name="attempted_at")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date attemptedAt;
	
	@Column(name="attempted_by", nullable=true)
	private int attemptedBy;

	public int getUserQuizId() {
		return userQuizId;
	}

	public void setUserQuizId(int userQuizId) {
		this.userQuizId = userQuizId;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getAttemptedAt() {
		return attemptedAt;
	}

	public void setAttemptedAt(Date attemptedAt) {
		this.attemptedAt = attemptedAt;
	}

	public int getAttemptedBy() {
		return attemptedBy;
	}

	public void setAttemptedBy(int attemptedBy) {
		this.attemptedBy = attemptedBy;
	}
	
	
	
}
