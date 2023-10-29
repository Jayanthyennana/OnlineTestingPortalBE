package com.otp.be.modal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="questions")
public class Questions {
	@Id
	@Column(name="question_id")
	private int questionId;
	
	@Column(name="question_desc")
	private String questionDesc;
	
	@Column(name="choice_1")
	private String choice1;
	
	@Column(name="choice_2")
	private String choice2;
	
	@Column(name="choice_3")
	private String choice3;
	
	@Column(name="choice_4")
	private String choice4;
	
	@Column(name="correct_choice")
	private String correctChoice;
	
	@Column(name="question_image")
	private byte[] questionImage;
	
	@Column(name="quiz_id")
	private int quizId;

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestionDesc() {
		return questionDesc;
	}

	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}

	public String getChoice1() {
		return choice1;
	}

	public void setChoice1(String choice1) {
		this.choice1 = choice1;
	}

	public String getChoice2() {
		return choice2;
	}

	public void setChoice2(String choice2) {
		this.choice2 = choice2;
	}

	public String getChoice3() {
		return choice3;
	}

	public void setChoice3(String choice3) {
		this.choice3 = choice3;
	}

	public String getChoice4() {
		return choice4;
	}

	public void setChoice4(String choice4) {
		this.choice4 = choice4;
	}

	public String getCorrectChoice() {
		return correctChoice;
	}

	public void setCorrectChoice(String correctChoice) {
		this.correctChoice = correctChoice;
	}

	public byte[] getQuestionImage() {
		return questionImage;
	}

	public void setQuestionImage(byte[] questionImage) {
		this.questionImage = questionImage;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
	
	

}
