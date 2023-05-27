package com.QuizeApplication.exceptions;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerAdviser {

	private static final Logger logger=LoggerFactory.getLogger(ControllerAdviser.class);
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Object> handlebadCredentionalsException(BadRequestException ex,WebRequest request,HttpServletRequest httpRequest){
		
		Map<String, Object> body = new LinkedHashMap<String,Object>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        
        logger.error("Error in calling {}",httpRequest.getRequestURI(),ex);
        
        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleMainException(Exception ex,WebRequest request,HttpServletRequest httpRequest){
		 Map<String, Object> body = new LinkedHashMap<String,Object>();
	     body.put("timestamp", LocalDateTime.now());
	     body.put("message", "Server error, Please contact IT Team");
	        
	     logger.error("Error in calling {}",httpRequest.getRequestURI(),ex);
	     
	     return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
