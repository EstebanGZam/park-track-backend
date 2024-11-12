package com.park_track.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UsernameAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT) // Código 409 Conflict
	public String handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(SexNotFoundException.class)
	public ResponseEntity<String> handleSexNotFoundException(SexNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler(TypeOfEvaluatedNotFoundException.class)
	public ResponseEntity<String> handleTypeOfEvaluatedNotFoundException(TypeOfEvaluatedNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
}
