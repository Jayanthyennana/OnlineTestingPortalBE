package com.otp.be.modal;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="quiz")
public class Quiz {

	@Id
	@Column(name="quiz_id")
	private int quizId;
	
	@Column(name="description")
	private String description;
	
	@Column(name="passcode")
	private String passcode;
	
	@Column(name="available_from")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date availableFrom;
	
	@Column(name="available_till")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date availableTill;
	
	@Column(name="total_marks")
	private int totalMarks;

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPasscode() {
		return passcode;
	}

	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	public Date getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(Date availableFrom) {
		this.availableFrom = availableFrom;
	}

	public Date getAvailableTill() {
		return availableTill;
	}

	public void setAvailableTill(Date availableTill) {
		this.availableTill = availableTill;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}
	
	
}
