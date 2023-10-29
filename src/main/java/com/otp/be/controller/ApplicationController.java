package com.otp.be.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.otp.be.modal.Questions;
import com.otp.be.modal.Quiz;
import com.otp.be.modal.User;
import com.otp.be.modal.UserQuiz;
import com.otp.be.services.QuestionsService;
import com.otp.be.services.QuizService;
import com.otp.be.services.UserQuizService;
import com.otp.be.services.UserService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class ApplicationController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private QuizService quizService;
	
	@Autowired
	private QuestionsService questionsService;
	
	@Autowired
	private UserQuizService userQuizService;
	
	@GetMapping("/users")
	public List<User> getUserDetails() {
		return userService.getUserDetails();
	}
	
	@GetMapping("/users/{userId}")
	public User getUserById(@PathVariable int userId) {
		return userService.getUserById(userId);
	}
	
	@PostMapping("/saveUser")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@GetMapping("/quiz")
	public List<Quiz> getQuizDetails() {
		return quizService.getQuizDetails();
	}
	
	@GetMapping("/quiz/{quizId}")
	public Quiz getQuizById(@PathVariable int quizId) {
		return quizService.getQuizById(quizId);
	}
	
	@GetMapping("/getIdList")
	public List<Integer> getIdList() {
		List<Quiz> quizDetails = quizService.getQuizDetails();
		List<Integer> idList = quizDetails.stream().map(Quiz::getQuizId).collect(Collectors.toList());
		return idList;
	}
	
	@PostMapping("/saveQuiz")
	public Quiz saveQuiz(@RequestBody Quiz quiz) {
		List<Quiz> quizDetails = quizService.getQuizDetails();
		List<Integer> idList = quizDetails.stream().map(Quiz::getQuizId).collect(Collectors.toList());
		int maxId = Collections.max(idList);
		quiz.setQuizId(maxId+1);
		
//		generate passcode
		Random random = new Random();
		String passcodeDigits = String.format("%04d", random.nextInt(10000));
		quiz.setPasscode("QZ-" + "" + passcodeDigits);
		DateFormat df=new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
		Date date=new Date();
		String finalDate=df.format(date);
		
		return quizService.saveQuiz(quiz);
	}
	
	@GetMapping("/questions")
	public List<Questions> getQuestionsDetails() {
		return questionsService.getQuestionsDetails();
	}
	
	@PostMapping("/saveQuestions")
	public Questions saveQuestions(@RequestBody Questions questions) {
		List<Questions> questionDetails = questionsService.getQuestionsDetails();
		List<Integer> idList = questionDetails.stream().map(Questions::getQuestionId).collect(Collectors.toList());
		int maxId = (idList.size() > 0) ? Collections.max(idList) : 0;
		questions.setQuestionId(maxId+1);
		return questionsService.saveQuestions(questions);
	}
	
	
	@GetMapping("/userQuizDetails")
	public List<UserQuiz> getUserQuizDetails() {
		return userQuizService.getUserQuizDetails();
		
	}
	
	@PostMapping("/saveUserQuiz")
	public UserQuiz saveUserQuizDetails(@RequestBody UserQuiz userQuiz) {
		return userQuizService.saveUserQuiz(userQuiz);
	}
	
}
