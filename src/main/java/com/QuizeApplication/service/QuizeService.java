package com.QuizeApplication.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QuizeApplication.entity.Quiz;
import com.QuizeApplication.enumvalue.QuizStatus;
import com.QuizeApplication.exceptions.BadRequestException;
import com.QuizeApplication.repository.QuizeRepository;

@Service
public class QuizeService {
	
	@Autowired
	private QuizeRepository quizeRepository;
	
	// createQuiz
	public Quiz createQuize(Quiz quiz){
		 quiz.setStatus(QuizStatus.INACTIVE);
		 System.out.println(quiz.getStatus());
	return quizeRepository.save(quiz);
		
	}
	
	// retrieve the all Quizes
	public List<Quiz> getAllCreatedQuiz(){
		
		return quizeRepository.findAll();
	}
	
	// retrieve the Quize By Id
	public Optional<Quiz> getByid(int id) {
		
		return quizeRepository.findById(id);
		
	}
	
	//retrieve the active quiz
	public Quiz getActivateQuery(int id) throws BadRequestException {
		
		Quiz qui=quizeRepository.findById(id).get();
		
		Quiz activatedQuiz;
		
		LocalDateTime now = LocalDateTime.now();
		
		System.out.println("CurrentDate"+now);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime endDate = LocalDateTime.parse(qui.getEndDate(), formatter);
        LocalDateTime startDate = LocalDateTime.parse(qui.getStartDate(), formatter);
        
            if ((now.isEqual(startDate)|now.isAfter(startDate)) && !now.equals(endDate)|now.isBefore(endDate)) {
            	qui.setStatus(QuizStatus.ACTIVE);
            	 activatedQuiz=qui;
            }else {
            	
            	throw new BadRequestException("Not Found any ative Quiz");
            	
            }
          
            return activatedQuiz;
            
}
	
	
		// retrieve the result after five minuts of a quiz
		public int quizResult(int id) throws BadRequestException {
			
			int result=0;
			Optional<Quiz> optionalQuiz = quizeRepository.findById(id);
			
			if(optionalQuiz.isPresent()) {
				Quiz quiz = optionalQuiz.get();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        LocalDateTime endDate = LocalDateTime.parse(quiz.getEndDate(), formatter);

//				 LocalDateTime endDateTime = quiz.getEndDate().plusMinutes(5);
				 
				 if(LocalDateTime.now().isAfter(endDate.plusMinutes(5))) {
					 
//					 System.out.println("hello this is your result ---------------------------------------"+quiz.getRightAnswer());
					  result= quiz.getRightAnswer();
				 }
				 
				 else {
					 throw new BadRequestException("Quiz result is not available yet");
				 }
				 
			}
			
			return result;
			
		}
		
		// Delete the quiz
		public void deleteQuizById(int id) {
			 
			quizeRepository.deleteById(id);
			
		}
}
