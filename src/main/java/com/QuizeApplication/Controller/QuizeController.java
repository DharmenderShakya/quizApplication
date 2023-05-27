package com.QuizeApplication.Controller;

import java.net.http.HttpRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.QuizeApplication.entity.Quiz;
import com.QuizeApplication.exceptions.BadRequestException;
import com.QuizeApplication.service.QuizeService;

@RestController
public class QuizeController {

	@Autowired
	private QuizeService quizeService;
	
	// create quiz
	@PostMapping("/creatquiz")
	public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
		quizeService.createQuize(quiz);
		return ResponseEntity.ok(quiz);
	}
	
	// get quiz by id
	
	@GetMapping("/retrieve/{id}")
	public void getQuizById(@PathVariable int id) {
		
		 quizeService.getByid(id);
	}
	
	
	// retrieve All created quizzes
	@GetMapping("/retrieveAllQuiz")
	public List<Quiz> getAllQuiz(){
		
		return quizeService.getAllCreatedQuiz();
		
	}
	
	// retrieve activated quiz
	@GetMapping("/getActivatedQuiz/{id}")
	
	public Quiz getActivatedQuiz(@PathVariable int id) throws BadRequestException {
		
		return quizeService.getActivateQuery(id);
	}
	
	
	// retrieve quiz result
	@GetMapping("/getResult/{id}")
	public int getQuizResult(@PathVariable int id) throws BadRequestException {
		
		return quizeService.quizResult(id);
	}
	
	// Delete Quiz
	public void deleteQuiz(@PathVariable int id) {
		
		quizeService.deleteQuizById(id);
	}
}
