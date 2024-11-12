package com.park_track.exception;

import org.springframework.http.HttpStatus;
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
}
