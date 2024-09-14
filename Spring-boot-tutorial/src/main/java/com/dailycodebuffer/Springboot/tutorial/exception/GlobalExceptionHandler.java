package com.dailycodebuffer.Springboot.tutorial.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotEmptyException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(NotEmptyException exception){
		return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
