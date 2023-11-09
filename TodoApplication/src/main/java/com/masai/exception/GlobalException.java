package com.masai.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<MyError> userNotFoundExceptionHandler(UserNotFoundException unfx, WebRequest wr){
		
		MyError error = new MyError(LocalDateTime.now(), unfx.getMessage(), wr.getDescription(false));
		return new ResponseEntity<MyError>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TodoNotFoundException.class)
	public ResponseEntity<MyError> todoNotFoundExceptionHandler(TodoNotFoundException tnfx, WebRequest wr){
		
		MyError error = new MyError(LocalDateTime.now(), tnfx.getMessage(), wr.getDescription(false));
		return new ResponseEntity<MyError>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyError> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException mrne, WebRequest wr){
		
		List<ObjectError> allErrors = mrne.getAllErrors();
		List<String> errorsToStringList = MethodArgumentNotValidException.errorsToStringList(allErrors);
		MyError error = new MyError(LocalDateTime.now(),String.join(",", errorsToStringList), wr.getDescription(false));
		
		return new ResponseEntity<MyError>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyError> allExceptionHandler(Exception ex, WebRequest wr){
		
		MyError error = new MyError(LocalDateTime.now(), ex.getMessage(), wr.getDescription(false));
		return new ResponseEntity<MyError>(error, HttpStatus.BAD_REQUEST);
	}

}
